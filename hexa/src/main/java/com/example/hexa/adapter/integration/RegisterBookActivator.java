package com.example.hexa.adapter.integration;

import com.example.hexa.application.port.in.RegisterBookUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

@MessageEndpoint
@RequiredArgsConstructor
@Slf4j
public class RegisterBookActivator {

    private final RegisterBookUseCase registerBookUseCase;

    @ServiceActivator(inputChannel = "bookChannel")
    public void registerBook(Message<RegisterBookUseCase.RegisterBookCommand> message) {
        RegisterBookUseCase.RegisterBookCommand payload = message.getPayload();
        log.debug("incoming registerBookCommand {}", payload.toString());
        registerBookUseCase.registerBook(payload);
    }

}
