package com.example.fbchatbot.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FacebookApiEndpoints {

    @Value("${facebookGraphApiUrl}")
    @Getter
    private String facebookGraphApiUrl;

    @Value("${pageAccessToken}")
    private String pageAccessToken;

    public String getUserApiUrl() {
        return facebookGraphApiUrl + "/{userId}?access_token={token}";
    }

    public String getSubscribeUrl() {
        return facebookGraphApiUrl + "/me/subscribed_apps";
    }

    public String getFacebookSendUrl() {
        return facebookGraphApiUrl + "/me/messages?access_token=" + pageAccessToken;
    }

    public String getFacebookMessengerProfileUrl() {
        return facebookGraphApiUrl + "/me/messenger_profile?access_token={PAGE_ACCESS_TOKEN}";
    }

}
