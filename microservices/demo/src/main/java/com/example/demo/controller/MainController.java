package com.example.demo.controller;

import com.example.accountmicroservices.controller.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final RestTemplate restTemplate;
    private final String dependencyUrl;

    @GetMapping(value = "mennyi", produces = MediaType.APPLICATION_JSON_VALUE)
    public Eredmeny miAzEletErtelme() {
        Result result = restTemplate.getForObject(dependencyUrl + "/mennyi", Result.class);
        return new Eredmeny(String.format("Az élet értelme: %s", result.getResult()));
    }

}
