package com.example.autonyilvantartocmd;

import com.example.autonyilvantartologic.Car;
import com.example.autonyilvantartologic.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class ListCarStrategy implements CarStrategy {

    private final CarService carService;

    @Override
    public void execute(String[] params) {
        if (params.length == 0) {
            list(carService.getCarList());
            return;
        }
        switch (params[0].toUpperCase()) {
            case "ORDERBY":
                if (params.length < 2) {
                    log.error("LIST ORDERBY must have one more parameter: ID or LICENSE");
                    return;
                }
                if (params[1].equalsIgnoreCase("ID")) {
                    list(carService.getCarListOrderById());
                }
                else if (params[1].equalsIgnoreCase("LICENSE")) {
                    list(carService.getCarListOrderByLicensePlateNumber());
                }
                else {
                    log.error("LIST ORDERBY unknown parameter: {}", params[1]);
                }
                break;
            case "ID-GT":
                if (params.length < 2) {
                    log.error("LIST ID-GT must have one more parameter.");
                    return;
                }
                list(carService.getCarListByIdGreaterThan(Long.parseLong(params[1])));
                break;
            default:
                log.error("Unknown command: LIST {}", params[0]);
                break;
        }
    }

    private void list(List<Car> carList) {
        for (Car car : carList) {
            log.info("Car: {}", car);
        }
    }

}
