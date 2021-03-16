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
        String account = "builder";
        String password = "builder";
        String status = "REGISTERED";
        String email = "Test01@gmail.com";
        String phoneNumber = "010-1111-3333";
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

        User u = User.builder().account(account).password(password).status(status).email(email).build();
        /*user.setCreatedAt(createdAt);
        user.setCreatedBy(createdBy);*/

        User newUser = userRepository.save(user);
        Assertions.assertNotNull(newUser);

    }
    @Test
    @Transactional
    public void read(){
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");
        /*user.setEmail();
        user.setAccount();*/
        //user entity에 chain 걸어두면 위에 것을 아래 형태로 변형 가능
        /*
        -----chain을 사용하기----
        user
                .setEmail("")
                .setPhoneNumber("")
                .setStatus("");
        User u = new User().setAccount("").setEmail("").setPassword("");*/

        user.getOrderGroupList().stream().forEach(orderGroup -> {
            System.out.println("---------------주문묶음---------------");
            System.out.println("수령인 :"+orderGroup.getRevName());
            System.out.println("수령지 :"+orderGroup.getRevAddress());
            System.out.println("총금액 :"+orderGroup.getTotalPrice());
            System.out.println("총수량 :"+orderGroup.getTotalQuantity());

            System.out.println("---------------주문상세---------------");
            orderGroup.getOrderDetailList().forEach(orderDetail ->{
                System.out.println("파트너사 이름 :"+orderDetail.getItem().getPartner().getName());
                System.out.println("파트너사 카테고리 :"+orderDetail.getItem().getPartner().getCategory().getTitle());
                System.out.println("주문 상품 :"+orderDetail.getItem().getName());
                System.out.println("고객센터 번호 :"+orderDetail.getItem().getPartner().getCallCenter());
                System.out.println("주문의 상태 :"+orderDetail.getStatus());
                System.out.println("도착예정일자 :"+orderDetail.getArrivalDate());

            });

        });
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
