package com.tutorial.carsservice.controller;

import com.tutorial.carsservice.entity.Car;
import com.tutorial.carsservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    CarService carService;

    @PostMapping
    public ResponseEntity<Car> save(@RequestBody Car car) {
        Car createdCar = carService.create(car);

        return new ResponseEntity(createdCar, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Car> update(@RequestBody Car car) {
        boolean existsCar = carService.existsCar(car.getId());
        Car updatedCar = carService.update(car);

        if (existsCar) {
            return ResponseEntity.noContent().build();
        }
        else {
            return new ResponseEntity(updatedCar, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateById(@PathVariable int id, @RequestBody Car car) {
        if (!carService.existsCar(id)) {
            return ResponseEntity.notFound().build();
        }

        car.setId(id);
        Car updatedCar = carService.update(car);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Car> deleteById(@PathVariable int id) {
        carService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAll() {
        List<Car> cars = carService.getAll();

        if (cars.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getById(@PathVariable int id) {
        Car car = carService.getById(id);

        if (car == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<Car>> getByUserId(@PathVariable int userId) {
        List<Car> cars = carService.getByUserId(userId);

        if (cars.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

}
