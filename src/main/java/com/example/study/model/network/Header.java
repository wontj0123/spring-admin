package com.example.study.model.network;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Header<T> {

    //api 통신시간
    @JsonProperty("transaction_time")//카멜케이스를 스네이크 케이스로 바꾸기
    //properties에 SNAKE_CASE 추가하면 어노테이션 다 안달아도 됨
    private LocalDateTime transactionTime; //LocalDateTime이 아니라 String 타입으로 맞춘다면
                                    //ISO, YYYY-MM-DD hh:mm:ss 매칭 방법을 다양하게 가능

    //api 응답 코드
    private String resultCode;

    //api 부가 설명
    private String description;

    private T data;

    //OK
    public static <T> Header<T> ok(){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build();
    }
    //DATA OK
    public static <T> Header<T> ok(T data){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build();
    }
    //ERROR
    public static <T> Header<T> ERROR(String description){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description(description)
                .build();
    }
}
