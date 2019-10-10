package com.example.autonyilvantartologic.car;

import java.util.List;

public interface CarService {

    List<Car> getCarList();

    void add(Car car);

    List<Car> getCarListOrderById();

    List<Car> getCarListOrderByLicensePlateNumber();

    List<Car> getCarListByIdGreaterThen(Long id);

}
