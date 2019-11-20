package com.example.accountmicroservices.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping(value = "mennyi", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result mennyi() {
        return new Result("hello");
    }

}
