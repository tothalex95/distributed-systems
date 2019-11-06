package com.example.fbchatbot.controller;

import com.example.fbchatbot.model.Callback;
import com.example.fbchatbot.model.Entry;
import com.example.fbchatbot.model.Event;
import com.example.fbchatbot.model.Message;
import com.example.fbchatbot.service.FacebookBotService;
import com.example.fbchatbot.service.FacebookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebMvcTest(FacebookController.class)
class FacebookControllerTest2 {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FacebookService facebookService;

    @MockBean
    private FacebookBotService facebookBotService;

    @Test
    void setupWebhookVerification() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/webhook")
                .param("hub.mode", "SUBSCRIBE")
                .param("hub.verify_token", "ASD1995WASD09DEF14")
                .param("hub.challenge", "asd"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("asd"));

        mvc.perform(MockMvcRequestBuilders
                .get("/webhook"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());


    }

    @Test
    void setupWebhookEndpoint() throws Exception {
        Callback callback = new Callback();
        callback.setObject("asd");
        mvc.perform(MockMvcRequestBuilders
                .post("/webhook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(callback)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        callback = new Callback();
        callback.setObject("page");
        Entry[] entries = new Entry[1];
        Entry entry = new Entry();
        Event[] events = new Event[1];
        Event event = new Event();
        event.setMessage(new Message());
        events[0] = event;
        entry.setMessaging(events);
        entries[0] = entry;
        callback.setEntry(entries);

        Mockito.when(facebookService.sendResponse(Mockito.any(Event.class))).thenReturn(ResponseEntity.ok().build());
        Mockito.when(facebookBotService.getResponse(Mockito.any(Event.class))).thenReturn(new Event());

        mvc.perform(MockMvcRequestBuilders
                .post("/webhook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(callback)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(facebookService, Mockito.times(1)).sendResponse(Mockito.any(Event.class));
        Mockito.verify(facebookBotService, Mockito.times(1)).getResponse(Mockito.any(Event.class));
    }
}