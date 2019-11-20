package com.example.demo;

import com.example.demo.controller.MainController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters = false)
public class DemoApplication {

    @Value("${account.service.url}")
    private String accountServiceUrl;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public MainController mainController(RestTemplate restTemplate) {
        return new MainController(restTemplate, accountServiceUrl);
    }

}
