package com.example.demo.controller;

import com.example.accountmicroservices.controller.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final WebClient webClient;

    @GetMapping(value = "mennyi", produces = MediaType.APPLICATION_JSON_VALUE)
    public Eredmeny miAzEletErtelme() {
        Result result = webClient.get()
                .uri("/mennyi")
                .retrieve()
                .bodyToMono(Result.class)
                .block();
        return new Eredmeny(String.format("Az élet értelme: %s", result.getResult()));
    }

}
