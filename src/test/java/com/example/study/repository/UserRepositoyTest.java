package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

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
        user.setPhoneNumber("010-1111-1111");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("admin");
        User newUser =userRepository.save(user);
        System.out.println("newUser: "+newUser);
    }
    public void read(){

    }
    public void update(){

    }
    public void delete(){

    }
}
