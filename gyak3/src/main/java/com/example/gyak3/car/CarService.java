package com.example.gyak3.car;

import java.util.List;

public interface CarService {

    List<Car> getCarList();

    void add(Car car);
}
