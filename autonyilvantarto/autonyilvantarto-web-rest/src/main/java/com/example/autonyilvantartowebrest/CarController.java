package com.example.autonyilvantartowebrest;

import com.example.autonyilvantartologic.Car;
import com.example.autonyilvantartologic.CarService;
import com.example.autonyilvantartowebrest.exceptions.CarNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("car")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CarController {

    private final CarService carService;

    @GetMapping
    public List<Car> getCarList(String order, Long id) {
        if (order != null) {
            if (order.equals("id")) {
                return carService.getCarListOrderById();
            }
            if (order.equals("license")) {
                return carService.getCarListOrderByLicensePlateNumber();
            }
        }
        if (id != null) {
            return carService.getCarListByIdGreaterThan(id);
        }
        return carService.getCarList();
    }

    @GetMapping("{id}")
    public Car getCar(@PathVariable Long id) {
        return carService.getCarList()
                .stream()
                .filter(c -> c.getId() == id)
                .findAny()
                .orElseThrow(CarNotFoundException::new);
    }

    @PostMapping
    public void saveCar(@Valid Car car) {
        carService.add(car);
    }

    @PutMapping("{id}")
    public void updateCar(@PathVariable Long id, @Valid Car car) {
        // TODO
    }

}
