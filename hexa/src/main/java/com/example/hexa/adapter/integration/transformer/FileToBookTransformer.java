package com.example.hexa.adapter.integration.transformer;

import com.example.hexa.adapter.RegisterBookResource;
import com.example.hexa.application.port.in.RegisterBookUseCase;
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
public class FileToBookTransformer {

    @Transformer(inputChannel = "bookSftpChannel", outputChannel = "bookChannel")
    public RegisterBookUseCase.RegisterBookCommand transform(Message<File> message) throws IOException {
        File payload = message.getPayload();
        log.debug("incoming filename {}", payload.toString());

        byte[] jsonData = Files.readAllBytes(payload.toPath());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonData, RegisterBookResource.class).toCommand();
    }

}
