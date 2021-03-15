package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
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
        String account = "Test01";
        String password = "Test01";
        String status = "REGISTERED";
        String email = "Test01@gmail.com";
        String phoneNumber = "010-1111-2222";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";


        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
        user.setCreatedAt(createdAt);
        user.setCreatedBy(createdBy);

        User newUser = userRepository.save(user);
        Assertions.assertNotNull(newUser);

    }
    @Test
    @Transactional
    public void read(){
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");
        Assertions.assertNotNull(user);

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
