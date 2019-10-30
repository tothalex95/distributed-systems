package com.example.fbchatbot.service;

import com.example.fbchatbot.model.Event;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class FacebookServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private FacebookApiEndpoints facebookApiEndpoints;

    @InjectMocks
    private FacebookService facebookService;

    @Test
    void sendResponse() {
        Mockito.when(facebookApiEndpoints.getFacebookSendUrl()).thenReturn("FB_URL");
        Mockito.when(restTemplate.postForEntity("FB_URL", Mockito.any(Event.class), String.class))
        .thenReturn(ResponseEntity.ok("asd"));

        assertEquals(ResponseEntity.ok("asd"), facebookService.sendResponse(new Event()));

        Mockito.reset(facebookApiEndpoints, restTemplate);
    }

}