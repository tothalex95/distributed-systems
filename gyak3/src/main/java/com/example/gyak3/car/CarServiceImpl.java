package com.example.gyak3.car;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private List<Car> carList = new ArrayList<>();

    @PostConstruct
    private void initialize() {
        carList.add(Car.builder().id(1).licensePlateNumber("ASD666").build());
        carList.add(Car.builder().id(2).licensePlateNumber("DEF001").build());
        carList.add(Car.builder().id(3).licensePlateNumber("LOL999").build());
    }

    @Override
    public List<Car> getCarList() {
        return Collections.unmodifiableList(carList);
    }

    @Override
    public void add(Car car) {
        carList.add(car);
    }

}
