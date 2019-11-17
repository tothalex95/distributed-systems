package com.example.hexa.adapter;

import com.example.hexa.application.port.in.RegisterBookUseCase;
import lombok.Data;

@Data
public class RegisterBookResource {

    private String title;

    private String author;

    public RegisterBookUseCase.RegisterBookCommand toCommand() {
        return new RegisterBookUseCase.RegisterBookCommand(title, author);
    }

}
