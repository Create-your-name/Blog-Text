package com.SpringBootBlog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
@MapperScan(value = "com.SpringBootBlog.dao")
public class SpringBoot_Blog {
    public static void main(String[] args) {
        SpringApplication.run(SpringBoot_Blog.class,args);
    }
}

