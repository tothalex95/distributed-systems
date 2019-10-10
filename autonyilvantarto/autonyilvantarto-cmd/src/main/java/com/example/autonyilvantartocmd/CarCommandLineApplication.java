package com.example.autonyilvantartocmd;

import com.example.autonyilvantartologic.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
public class CarCommandLineApplication implements CommandLineRunner {

    @Autowired
    private CarCommandInterpreter carCommandInterpreter;

    public static void main(String[] args) {
        SpringApplication.run(CarCommandLineApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        carCommandInterpreter.interpret(args);
    }

    @Bean
    public Map<String, CarStrategy> carStrategyMap(CarService carService) {
        Map<String, CarStrategy> carStrategyMap = new HashMap<>();
        carStrategyMap.put("ADD", new AddCarStrategy(carService));
        carStrategyMap.put("LIST", new ListCarStrategy(carService));
        return carStrategyMap;
    }

}
