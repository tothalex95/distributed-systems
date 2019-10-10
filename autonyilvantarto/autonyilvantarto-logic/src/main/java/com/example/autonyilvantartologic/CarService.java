package com.example.autonyilvantartologic;

import java.util.List;

public interface CarService {

    List<Car> getCarList();

    void add(Car car);

    List<Car> getCarListOrderById();

    List<Car> getCarListOrderByLicensePlateNumber();

    List<Car> getCarListByIdGreaterThan(Long id);

}
