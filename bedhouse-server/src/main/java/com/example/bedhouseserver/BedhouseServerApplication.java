package com.example.bedhouseserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.bedhouseserver.mapper")
public class BedhouseServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BedhouseServerApplication.class, args);
    }

}
