package com.tutorial.carsservice.service;

import com.tutorial.carsservice.entity.Car;
import com.tutorial.carsservice.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAll() {
        return carRepository.findAll();
    }

    public Car getById(int id) {
        return carRepository.findById(id).orElse(null);
    }

    public boolean existsCar(int id) {
        return carRepository.existsById(id);
    }

    public Car create(Car car) {
        return carRepository.save(car);
    }

    public Car update(Car car) {
        Car oldCar = carRepository.findById(car.getId()).orElse(new Car());

        oldCar.setBrand(car.getBrand());
        oldCar.setModel(car.getModel());
        oldCar.setUserId(car.getUserId());

        return carRepository.save(oldCar);
    }

    public void deleteById(int id) {
        carRepository.deleteById(id);
    }

    public List<Car> getByUserId(int userId) {
        return carRepository.findByUserId(userId);
    }

}
