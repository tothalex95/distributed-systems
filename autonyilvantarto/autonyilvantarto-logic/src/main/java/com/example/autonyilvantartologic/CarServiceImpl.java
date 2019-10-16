package com.example.autonyilvantartologic;

import com.example.autonyilvantartologic.exceptions.CarIdMustBeUniqueException;
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
        carList.add(Car.builder().id(1).licensePlateNumber("ABC-123").build());
        carList.add(Car.builder().id(2).licensePlateNumber("DEF-456").build());
        carList.add(Car.builder().id(4).licensePlateNumber("GHI-789").build());
        carList.add(Car.builder().id(3).licensePlateNumber("ASD-666").build());
    }

    @Override
    public List<Car> getCarList() {
        return Collections.unmodifiableList(carList);
    }

    @Override
    public void add(Car car) {
        if (carList.stream().anyMatch(c -> c.getId() == car.getId())) {
            throw new CarIdMustBeUniqueException("Car ID must be unique!");
        }
        carList.add(car);
    }

    @Override
    public List<Car> getCarListOrderById() {
        List<Car> sortedCarList = new ArrayList<>(carList);
        sortedCarList.sort(Comparator.comparing(Car::getId));
        return sortedCarList;
    }

    @Override
    public List<Car> getCarListOrderByLicensePlateNumber() {
        List<Car> sortedCarList = new ArrayList<>(carList);
        sortedCarList.sort(Comparator.comparing(Car::getLicensePlateNumber));
        return sortedCarList;
    }

    @Override
    public List<Car> getCarListByIdGreaterThan(Long id) {
        return id == null ? getCarList() : carList.stream().filter(c -> c.getId() > id).collect(Collectors.toList());
    }

}
