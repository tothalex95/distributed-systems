package com.example.fbchatbot.service;

import com.example.fbchatbot.model.Event;
import com.example.fbchatbot.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FacebookService {

    private final RestTemplate restTemplate;

    private final FacebookApiEndpoints facebookApiEndpoints;

    public User getUser(String id, String pageAccessToken) {
        return restTemplate.getForEntity(facebookApiEndpoints.getUserApiUrl(), User.class, id, pageAccessToken).getBody();
    }

    public ResponseEntity<String> sendResponse(Event event) {
        try {
            return restTemplate.postForEntity(facebookApiEndpoints.getFacebookSendUrl(), event, String.class);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        }
    }

}
