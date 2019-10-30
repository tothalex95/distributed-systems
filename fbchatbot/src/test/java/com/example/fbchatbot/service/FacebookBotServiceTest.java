package com.example.fbchatbot.service;

import com.example.fbchatbot.model.Event;
import com.example.fbchatbot.model.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FacebookBotServiceTest {

    private FacebookBotService facebookBotService;

    @BeforeEach
    public void setUp() {
        facebookBotService = new FacebookBotService();
    }

    @Test
    void getResponse() {
        Event event = new Event();

        event.setMessage(new Message().setText("hello"));
        assertEquals("hello", facebookBotService.getResponse(event).getMessage().getText());

        event.setMessage(new Message().setText("mizu?"));
        assertEquals("nemtom mi bajod", facebookBotService.getResponse(event).getMessage().getText());

        event.setMessage(null);
        assertEquals("nemtom mi bajod", facebookBotService.getResponse(event).getMessage().getText());
    }
}