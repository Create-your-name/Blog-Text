package com.SpringBootBlog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.SpringBootBlog")
public class SpringBoot_Blog {
    public static void main(String[] args) {
        SpringApplication.run(SpringBoot_Blog.class,args);
    }
}

