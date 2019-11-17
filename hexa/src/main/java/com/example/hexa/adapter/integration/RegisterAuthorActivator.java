package com.example.hexa.adapter.integration;

import com.example.hexa.application.port.in.RegisterAuthorUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

@MessageEndpoint
@RequiredArgsConstructor
@Slf4j
public class RegisterAuthorActivator {

    private final RegisterAuthorUseCase registerAuthorUseCase;

    @ServiceActivator(inputChannel = "authorChannel")
    public void registerAuthor(Message<RegisterAuthorUseCase.RegisterAuthorCommand> message) {
        RegisterAuthorUseCase.RegisterAuthorCommand payload = message.getPayload();
        log.debug("incoming registerAuthorCommand {}", payload.toString());
        registerAuthorUseCase.registerAuthor(payload);
    }

}
