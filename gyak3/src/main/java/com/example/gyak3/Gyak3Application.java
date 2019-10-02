package com.example.gyak3;

import com.example.gyak3.car.CarService;
import com.example.gyak3.car.CarServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Gyak3Application {

    public static void main(String[] args) {
        SpringApplication.run(Gyak3Application.class, args);
    }

    /*@Bean
    public CarService getCarService() {
        return new CarServiceImpl();
    }*/

}
