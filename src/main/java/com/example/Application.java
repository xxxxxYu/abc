package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;


//@SpringBootApplication(scanBasePackages = "com")
@SpringBootApplication
@MapperScan("com.example.dao")

//@EnableAutoConfiguration
//@ComponentScan(basePackages = "com.example.controller")


//@SpringBootApplication

public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
