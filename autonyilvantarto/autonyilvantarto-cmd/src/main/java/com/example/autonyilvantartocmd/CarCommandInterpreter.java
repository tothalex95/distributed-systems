package com.example.autonyilvantartocmd;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class CarCommandInterpreter {

    private final Map<String, CarStrategy> carStrategyMap;

    public void interpret(String[] command) {
        log.info("Interpret command: {}", String.join(" ", command));

        carStrategyMap.get(command[0]).execute(Arrays.stream(command).skip(1).collect(Collectors.toList()).toArray(new String[command.length - 1]));
    }

}
