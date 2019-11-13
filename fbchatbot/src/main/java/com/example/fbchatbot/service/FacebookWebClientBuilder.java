package com.example.fbchatbot.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class FacebookWebClientBuilder {

    public WebClient build(String url) {
        return WebClient.create(url);
    }

}
