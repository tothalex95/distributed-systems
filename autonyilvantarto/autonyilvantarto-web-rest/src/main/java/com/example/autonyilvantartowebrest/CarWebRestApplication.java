package com.example.autonyilvantartowebrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
public class CarWebRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarWebRestApplication.class, args);
    }

}
