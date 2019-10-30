package com.example.autonyilvantartoweb;

import com.example.autonyilvantartologic.Car;
import com.example.autonyilvantartologic.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("car")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class CarController {

    @Value("${spring.application.name}")
    private String appName;

    private final CarService carService;

    @GetMapping
    public ModelAndView getCarList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("carList");
        modelAndView.addObject("carList", carService.getCarList());
        modelAndView.addObject("appName", appName);
        return modelAndView;
    }

    @GetMapping("id-order")
    public ModelAndView getCarListOrderById() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("carList", carService.getCarListOrderById());
        modelAndView.addObject("appName", appName);
        modelAndView.setViewName("carList");
        return modelAndView;
    }

    @GetMapping("license-order")
    public ModelAndView getCarListOrderByLicensePlateNumber() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("carList", carService.getCarListOrderByLicensePlateNumber());
        modelAndView.addObject("appName", appName);
        modelAndView.setViewName("carList");
        return modelAndView;
    }

    @GetMapping("id-gt/{id}")
    public ModelAndView getCarListByIdGreaterThan(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("carList", carService.getCarListByIdGreaterThan(id));
        modelAndView.addObject("appName", appName);
        modelAndView.setViewName("carList");
        return modelAndView;
    }

    @GetMapping("add")
    public ModelAndView firstAdd() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("car", Car.builder().build());
        modelAndView.setViewName("addCar");
        return modelAndView;
    }

    @PostMapping("add")
    public ModelAndView add(@Valid Car car, BindingResult bindingResult) {
        log.debug("car {}", car);
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("car", car);
            modelAndView.setViewName("addCar");
        } else {
            carService.add(car);
            modelAndView.setViewName("redirect:/car");
        }

        return modelAndView;
    }

}
