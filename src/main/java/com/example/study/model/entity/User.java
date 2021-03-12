package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor//모든 매개변수를 생성 해주는 어노테이션
@NoArgsConstructor
@Entity //entity 임을 명시 table이랑 같은 개념
//@Table(name = "User") table 이름 명시, 하지만 클래스 이름이랑 테이블 명이 같으면 명시할 필요없음
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    private String email;

    private String phoneNumber;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    //1:N 자신을 기준으로 orderDetail이랑 1:N관계
    @OneToMany(fetch = FetchType.LAZY , mappedBy = "user")//어떤 컬럼에 매핑 시킬지 userDetail에있는 변수 명과 동일해야됨
    private List<OrderDetail> orderDetailList;

}
