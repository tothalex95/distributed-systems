package com.example.hexa.adapter.web;

import com.example.hexa.adapter.RegisterAuthorResource;
import com.example.hexa.application.port.in.RegisterAuthorUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterAuthorController {

    private final RegisterAuthorUseCase registerAuthorUseCase;

    @PostMapping("authors/register")
    public void register(@RequestBody RegisterAuthorResource registerAuthorResource) {
        registerAuthorUseCase.registerAuthor(registerAuthorResource.toCommand());
    }

}
