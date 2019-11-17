package com.example.hexa.adapter;

import com.example.hexa.application.port.in.RegisterAuthorUseCase;
import lombok.Data;

@Data
public class RegisterAuthorResource {

    private String name;

    public RegisterAuthorUseCase.RegisterAuthorCommand toCommand() {
        return new RegisterAuthorUseCase.RegisterAuthorCommand(name);
    }

}
