package com.example.autonyilvantartoweb;

import com.example.autonyilvantartologic.Car;
import com.example.autonyilvantartologic.CarService;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class CarControllerTest {

    @MockBean
    private CarService carService;

    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    public void tearDown() {
        Mockito.reset(carService);
    }

    @Test
    public void getCarListTest() throws Exception {
        List<Car> carList = new ArrayList<>();
        carList.add(Car.builder().id(1).licensePlateNumber("AAA-111").build());
        carList.add(Car.builder().id(2).licensePlateNumber("BBB-222").build());
        carList.add(Car.builder().id(3).licensePlateNumber("CCC-333").build());
        Mockito.when(carService.getCarList()).thenReturn(carList);

        Map<String, Object> model = mockMvc.perform(MockMvcRequestBuilders.get("/car"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("carList"))
                .andReturn().getModelAndView().getModel();

        assertEquals(carList, model.get("carList"));
    }

    @Test
    public void getCarListOrderByIdTest() throws Exception {
        List<Car> carList = new ArrayList<>();
        carList.add(Car.builder().id(1).licensePlateNumber("AAA-111").build());
        carList.add(Car.builder().id(2).licensePlateNumber("BBB-222").build());
        carList.add(Car.builder().id(3).licensePlateNumber("CCC-333").build());
        Mockito.when(carService.getCarListOrderById()).thenReturn(carList);

        Map<String, Object> model = mockMvc.perform(MockMvcRequestBuilders.get("/car/id-order"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("carList"))
                .andReturn().getModelAndView().getModel();

        assertEquals(carList, model.get("carList"));
    }

    @Test
    public void getCarListOrderByLicensePlateNumberTest() throws Exception {
        List<Car> carList = new ArrayList<>();
        carList.add(Car.builder().id(1).licensePlateNumber("AAA-111").build());
        carList.add(Car.builder().id(2).licensePlateNumber("BBB-222").build());
        carList.add(Car.builder().id(3).licensePlateNumber("CCC-333").build());
        Mockito.when(carService.getCarListOrderByLicensePlateNumber()).thenReturn(carList);

        Map<String, Object> model = mockMvc.perform(MockMvcRequestBuilders.get("/car/license-order"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("carList"))
                .andReturn().getModelAndView().getModel();

        assertEquals(carList, model.get("carList"));
    }

    @Test
    public void getCarListByIdGreaterThanTest() throws Exception {
        List<Car> carList = new ArrayList<>();
        carList.add(Car.builder().id(2).licensePlateNumber("BBB-222").build());
        carList.add(Car.builder().id(3).licensePlateNumber("CCC-333").build());
        Mockito.when(carService.getCarListByIdGreaterThan(1L)).thenReturn(carList);

        Map<String, Object> model = mockMvc.perform(MockMvcRequestBuilders.get("/car/id-gt/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("carList"))
                .andReturn().getModelAndView().getModel();

        assertEquals(carList, model.get("carList"));
    }

    @Test
    public void firstAddTest() throws Exception {
        Map<String, Object> model = mockMvc.perform(MockMvcRequestBuilders.get("/car/add"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("addCar"))
                .andReturn().getModelAndView().getModel();

        assertEquals(Car.builder().build(), model.get("car"));
    }

    @Test
    public void addTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/car/add")
                .param("id", "4")
                .param("licensePlateNumber", "DDD-444"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/car"));
    }

}