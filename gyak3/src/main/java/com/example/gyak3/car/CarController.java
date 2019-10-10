package com.example.gyak3.car;

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

//    @GetMapping
//    public ModelAndView getCarList() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("carList");
//        modelAndView.addObject("carList", carService.getCarList());
//        modelAndView.addObject("appName", appName);
//        return modelAndView;
//    }
//
//    @GetMapping("id-order")
//    public ModelAndView getCarListOrderById() {
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("carList", carService.getCarListOrderById());
//        mav.addObject("appName", appName);
//        mav.setViewName("carList");
//        return mav;
//    }
//
//    @GetMapping("license-order")
//    public ModelAndView getCarListOrderByLicensePlateNumber() {
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("carList", carService.getCarListOrderByLicensePlateNumber());
//        mav.addObject("appName", appName);
//        mav.setViewName("carList");
//        return mav;
//    }
//
//    @GetMapping("id-gt/{id}")
//    public ModelAndView getCarListByIdGreaterThan(@PathVariable Long id) {
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("carList", carService.getCarListByIdGreaterThen(id));
//        mav.addObject("appName", appName);
//        mav.setViewName("carList");
//        return mav;
//    }
//
//    @GetMapping("add")
//    public ModelAndView firstAdd() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("car", new Car());
//        modelAndView.setViewName("addCar");
//        return modelAndView;
//    }
//
//    @PostMapping("add")
//    public ModelAndView add(@Valid Car car, BindingResult bindingResult) {
//        log.debug("car {}", car);
//        ModelAndView modelAndView = new ModelAndView();
//
//        if (bindingResult.hasErrors()) {
//            modelAndView.addObject("car", car);
//            modelAndView.setViewName("addCar");
//        }
//        else {
//            carService.add(car);
//            modelAndView.setViewName("redirect:/car");
//        }
//
//        return modelAndView;
//    }

}
