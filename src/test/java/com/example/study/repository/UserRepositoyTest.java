package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
        Optional<User> user =userRepository.findById(2L);
        user.ifPresent(selectUser ->{
            System.out.println("user : "+selectUser);
            System.out.println("email : "+selectUser.getEmail());
        });
    }
    @Test
    public void update(){
        Optional<User> user =userRepository.findById(2L);
        user.ifPresent(selectUser ->{
            selectUser.setAccount("PPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });

    }
    public void delete(){

    }
}
