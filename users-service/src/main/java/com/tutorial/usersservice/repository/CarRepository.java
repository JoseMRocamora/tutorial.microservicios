package com.tutorial.usersservice.repository;

import com.tutorial.usersservice.model.Car;

import java.util.List;

public interface CarRepository {

    List<Car> getCarsByUserId(int userId);

}
