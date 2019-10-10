package com.example.autonyilvantartologic.car;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private List<Car> carList = new ArrayList<>();

    @PostConstruct
    private void initialize() {
        carList.add(Car.builder().id(2).licensePlateNumber("DEF001").build());
        carList.add(Car.builder().id(1).licensePlateNumber("ASD666").build());
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

    @Override
    public List<Car> getCarListOrderById() {
        List<Car> orderedCarList = new ArrayList<>(carList);
        orderedCarList.sort(Comparator.comparing(Car::getId));
        return orderedCarList;
    }

    @Override
    public List<Car> getCarListOrderByLicensePlateNumber() {
        List<Car> orderedCarList = new ArrayList<>(carList);
        orderedCarList.sort(Comparator.comparing(Car::getLicensePlateNumber));
        return orderedCarList;
    }

    @Override
    public List<Car> getCarListByIdGreaterThen(Long id) {
        if (id == null) {
            return getCarList();
        }
        return carList.stream().filter(c -> c.getId() > id).collect(Collectors.toList());
    }

}
