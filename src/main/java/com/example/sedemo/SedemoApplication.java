package com.example.sedemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.sedemo.mapper")
public class SedemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SedemoApplication.class, args);
    }

}
