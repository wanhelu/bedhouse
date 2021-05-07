package com.example.bedhouseserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//TODO　各模块表格分页以及排序、筛选功能的后端迁移
@SpringBootApplication
@MapperScan("com.example.bedhouseserver.mapper")
public class BedhouseServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BedhouseServerApplication.class, args);
    }

}
