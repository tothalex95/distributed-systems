package com.example.autonyilvantartocmd;

import com.example.autonyilvantartologic.Car;
import com.example.autonyilvantartologic.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class AddCarStrategy implements CarStrategy {

    private final CarService carService;

    @Override
    public void execute(String[] params) {
        if (params.length != 2) {
            log.error("ADD command must have 2 parameters: id and license plate number.");
            return;
        }
        carService.add(Car.builder().id(Long.parseLong(params[0])).licensePlateNumber(params[1]).build());
        log.info("Car added successfully.");
    }
}
