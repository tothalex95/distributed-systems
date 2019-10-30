package com.example.fbchatbot.controller;

import com.example.fbchatbot.model.Callback;
import com.example.fbchatbot.model.Entry;
import com.example.fbchatbot.model.Event;
import com.example.fbchatbot.model.EventType;
import com.example.fbchatbot.service.FacebookBotService;
import com.example.fbchatbot.service.FacebookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FacebookController {

    private final FacebookService facebookService;

    private final FacebookBotService facebookBotService;

    @Value("${botToken}")
    private String botToken;

    @Value("${pageAccessToken}")
    private String pageAccessToken;

    @GetMapping("webhook")
    public ResponseEntity setupWebhookVerification(
            @RequestParam("hub.mode") String mode,
            @RequestParam("hub.verify_token") String verifyToken,
            @RequestParam("hub.challenge") String challenge
    ) {
        if (EventType.SUBSCRIBE.name().equalsIgnoreCase(mode) && botToken.equals(verifyToken)) {
            return ResponseEntity.ok(challenge);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping(value = "webhook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity setupWebhookEndpoint(@RequestBody Callback callback) {
        if (!callback.getObject().equals("page")) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        for (Entry entry : callback.getEntry()) {
            for (Event event : entry.getMessaging()) {
                if (event.getMessage() != null) {
                    event.setType(EventType.MESSAGE);
                    facebookService.sendResponse(facebookBotService.getResponse(event));
                }
            }
        }
        return ResponseEntity.ok("EVENT_RECEIVED");
    }

}
