package com.tutorial.usersservice.controller;

import com.tutorial.usersservice.entity.User;
import com.tutorial.usersservice.model.Bike;
import com.tutorial.usersservice.model.Car;
import com.tutorial.usersservice.model.UserVehicles;
import com.tutorial.usersservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        User createdUser = userService.create(user);

        return new ResponseEntity(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/{userId}/cars")
    public ResponseEntity<Car> createCar(@PathVariable int userId, @RequestBody Car car) {
        if (isNotValidUserId(userId)) {
            return ResponseEntity.notFound().build();
        }

        Car createdCar = userService.createCar(userId, car);
        return new ResponseEntity(createdCar, HttpStatus.CREATED);
    }

    @PostMapping("/{userId}/bikes")
    public ResponseEntity<Bike> createBike(@PathVariable int userId, @RequestBody Bike bike) {
        if (isNotValidUserId(userId)) {
            return ResponseEntity.notFound().build();
        }

        Bike createdBike = userService.createBike(userId, bike);
        return new ResponseEntity(createdBike, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user) {
        boolean existsUser = userService.existsUser(user.getId());
        User updatedUser = userService.update(user);

        if (existsUser) {
            return ResponseEntity.noContent().build();
        }
        else {
            return new ResponseEntity(updatedUser, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateById(@PathVariable int id, @RequestBody User user) {
        if (!userService.existsUser(id)) {
            return ResponseEntity.notFound().build();
        }

        user.setId(id);
        User updatedUser = userService.update(user);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Bike> deleteById(@PathVariable int id) {
        userService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.getAll();

        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable int id) {
        if (isNotValidUserId(id)) {
            return ResponseEntity.notFound().build();
        }

        User user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}/cars")
    public ResponseEntity<List<Car>> getUserCars(@PathVariable int userId) {
        if (isNotValidUserId(userId)) {
            return ResponseEntity.notFound().build();
        }

        List<Car> cars = userService.getUserCars(userId);
        if (CollectionUtils.isEmpty(cars)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{userId}/bikes")
    public ResponseEntity<List<Bike>> getUserBikes(@PathVariable int userId) {
        if (isNotValidUserId(userId)) {
            return ResponseEntity.notFound().build();
        }

        List<Bike> bikes = userService.getUserBikes(userId);
        if (CollectionUtils.isEmpty(bikes)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bikes);
    }

    @GetMapping("/{userId}/vehicles")
    public ResponseEntity<UserVehicles> getUserVehicles(@PathVariable int userId) {
        if (isNotValidUserId(userId)) {
            return ResponseEntity.notFound().build();
        }

        UserVehicles userVehicles = userService.getUserVehicles(userId);
        return ResponseEntity.ok(userVehicles);
    }
    private boolean isNotValidUserId(int userId) {
        return !userService.existsUser(userId);
    }

}