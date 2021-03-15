package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderDetail;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
public class OrderDetailRepositoryTest extends StudyApplicationTests {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Test
    public void Create(){
        OrderDetail orderDetail = new OrderDetail();

        //orderDetail.setOrderAt(LocalDateTime.now());

        orderDetail.setStatus("WAITING");
        orderDetail.setArrivalDate(LocalDateTime.now().plusDays(2));//현재일자 더하기 2일로 나타남
        orderDetail.setQuantity(1);
        orderDetail.setTotalPrice(BigDecimal.valueOf(900000));


        orderDetail.setOrderGroupId(1L);    // 어떠한 장바구니에
        orderDetail.setItemId(1L);          // 어떠한 상품


        orderDetail.setCreatedAt(LocalDateTime.now());
        orderDetail.setCreatedBy("AdminServer");
        OrderDetail newOrderDetail =orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(newOrderDetail);
    }
}
