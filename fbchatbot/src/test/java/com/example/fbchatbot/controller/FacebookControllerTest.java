package com.example.fbchatbot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FacebookControllerTest {

    @Autowired
    private FacebookController facebookController;

    @Test
    void contextLoads() {
        assertNotNull(facebookController);
    }

    @Test
    void setupWebhookVerification() {
        ResponseEntity responseEntity = facebookController.setupWebhookVerification("asd", "asd", "asd");
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());

        responseEntity = facebookController.setupWebhookVerification("SUBSCRIBE", "ASD1995WASD09DEF14", "asd");
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("asd", responseEntity.getBody());
    }

    @Test
    void setupWebhookEndpoint() {
        ResponseEntity responseEntity = facebookController.setupWebhookEndpoint(null);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}