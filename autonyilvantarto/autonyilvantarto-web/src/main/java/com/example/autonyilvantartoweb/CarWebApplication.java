package com.example.autonyilvantartoweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
public class CarWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarWebApplication.class, args);
    }

}
