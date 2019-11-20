package com.example.accountmicroservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AccountMicroservicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountMicroservicesApplication.class, args);
    }

}
