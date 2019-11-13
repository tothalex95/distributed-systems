package com.example.hexa.application.port.in;

import lombok.Data;

import javax.validation.constraints.NotBlank;

public interface RegisterAuthorUseCase {

    void registerAuthor(RegisterAuthorCommand command);

    @Data
    final class RegisterAuthorCommand extends SelfValidating {

        @NotBlank
        private final String name;

        public RegisterAuthorCommand(String name) {
            this.name = name;
            validateSelf();
        }

    }

}
