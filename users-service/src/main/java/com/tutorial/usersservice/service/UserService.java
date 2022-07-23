package com.tutorial.usersservice.service;

import com.tutorial.usersservice.entity.User;
import com.tutorial.usersservice.model.Bike;
import com.tutorial.usersservice.model.Car;
import com.tutorial.usersservice.model.UserVehicles;
import com.tutorial.usersservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    BikeRepository bikeRepository;

    @Autowired
    CarFeignClient carFeignClient;

    @Autowired
    BikeFeignClient bikeFeignClient;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean existsUser(int userId) {
        return getById(userId) != null;
    }

    public User create(User user) {
        return  userRepository.save(user);
    }

    public User update(User user) {
        User oldUser = userRepository.findById(user.getId()).orElse(new User());

        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());

        return  userRepository.save(oldUser);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }
    public List<Car> getUserCars(int userId) {
        return carRepository.getCarsByUserId(userId);
    }

    public List<Bike> getUserBikes(int userId) {
        return bikeRepository.getBikesByUserId(userId);
    }

    public Car createCar(int userId, Car car){
        car.setUserId(userId);
        return carFeignClient.create(car);
    }

    public Bike createBike(int userId, Bike bike){
        bike.setUserId(userId);
        return bikeFeignClient.create(bike);
    }

    public UserVehicles getUserVehicles(int userId) {
        User user = getById(userId);
        List<Car> cars = getUserCars(userId);
        List<Bike> bikes = getUserBikes(userId);

        return new UserVehicles(user.getId(), user.getName(), user.getEmail(), cars, bikes);
    }
}