package com.example.hexa.adapter.web;

import com.example.hexa.adapter.RegisterBookResource;
import com.example.hexa.application.port.in.RegisterBookUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("books")
@Slf4j
public class RegisterBookController {

    private final RegisterBookUseCase registerBookUseCase;

    @PostMapping("register")
    public void register(@RequestBody RegisterBookResource registerBookResource) {
        log.debug("incoming registerBookResource {}", registerBookResource);
        registerBookUseCase.registerBook(registerBookResource.toCommand());
    }

}
