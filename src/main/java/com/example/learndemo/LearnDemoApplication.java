package com.example.learndemo;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class LearnDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnDemoApplication.class, args);
    }

}
