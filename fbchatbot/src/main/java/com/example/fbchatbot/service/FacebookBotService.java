package com.example.fbchatbot.service;

import com.example.fbchatbot.model.Event;
import com.example.fbchatbot.model.Message;
import org.springframework.stereotype.Service;

@Service
public class FacebookBotService {

    public Event getResponse(Event event) {
        Event response = new Event()
                .setMessagingType("RESPONSE")
                .setRecipient(event.getSender());
        if (event.getMessage() == null || !"hello".equals(event.getMessage().getText())) {
            response.setMessage(new Message().setText("nemtom mi bajod"));
        }
        else {
            response.setMessage(new Message().setText("hello"));
        }
        return response;
    }

}
