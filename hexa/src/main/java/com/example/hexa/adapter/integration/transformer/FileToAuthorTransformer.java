package com.example.hexa.adapter.integration.transformer;

import com.example.hexa.adapter.RegisterAuthorResource;
import com.example.hexa.application.port.in.RegisterAuthorUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@MessageEndpoint
@Slf4j
public class FileToAuthorTransformer {

    @Transformer(inputChannel = "authorFileNameChannel", outputChannel = "authorChannel")
    public RegisterAuthorUseCase.RegisterAuthorCommand transform(Message<File> message) throws IOException {
        File payload = message.getPayload();
        log.debug("incoming filename {}", payload.toString());

        byte[] jsonData = Files.readAllBytes(payload.toPath());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonData, RegisterAuthorResource.class).toCommand();
    }

}
