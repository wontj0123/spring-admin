package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoyTest extends StudyApplicationTests {
    //의존성 주입(DI) Dependency Injection
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        // String sql = insert into user (%s, %s , %d ) value (account, email, age);
        User user = new User();
        user.setAccount("TestUser");
        user.setEmail("TestUser@gmail.com");
        user.setPhoneNumber("010-1111-3333");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("admin4");
        User newUser =userRepository.save(user);
        System.out.println("newUser: "+newUser);
    }
    @Test
    public void read(){
        //비어 있을 수도 있어서 Optional 사용
        Optional<User> user =userRepository.findById(2L);//Long 타입의 2번 아이디 검색
        user.ifPresent(selectUser ->{
            System.out.println("user : "+selectUser);
            System.out.println("email : "+selectUser.getEmail());
        });
    }
    @Test
    public void update(){
        Optional<User> user =userRepository.findById(2L);//Long 타입의 2번 아이디 검색
        user.ifPresent(selectUser ->{
            selectUser.setAccount("PPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });

    }
    @Test
    @Transactional //데이터를 삭제했다가 롤백으로 다시 돌려 놓음
    public void delete(){
        Optional<User> user =userRepository.findById(3L);//Long 타입의 3번 아이디 검색

        Assert.assertTrue(user.isPresent());//true 인지 검사하고 다음 라인 진행

        user.ifPresent(selectUser ->{
            userRepository.delete(selectUser);
        });
        Optional<User> deleteUser =userRepository.findById(3L);

        Assert.assertFalse(deleteUser.isPresent());//false

        /*if(deleteUser.isPresent()){
            System.out.println("데이터 존재 :"+deleteUser.get());
        }else {
            System.out.println("데이터 삭제 데이터 없음");
        }*/
    }
}
