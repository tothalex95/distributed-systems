package com.example.hexa.application.port.in;

import lombok.Data;

import javax.validation.constraints.NotBlank;

public interface RegisterBookUseCase {

    void registerBook(RegisterBookCommand command);

    @Data
    final class RegisterBookCommand extends SelfValidating {

        @NotBlank
        private String title;

        @NotBlank
        private String author;

        public RegisterBookCommand(String title, String author) {
            this.title = title;
            this.author = author;
            validateSelf();
        }

    }

}
