package com.tutorial.usersservice.repository;

import com.tutorial.usersservice.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
public class CarRepositoryImpl implements CarRepository {

    private static final String URL_CARS_BY_USER = "http://localhost:8002/cars/by-user/%d";

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Car> getCarsByUserId(int userId) {
        List<Car> cars = restTemplate.getForObject(String.format(URL_CARS_BY_USER, userId), List.class);
        return cars;
    }

}
