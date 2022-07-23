package com.tutorial.bikesservice.service;

import com.fasterxml.jackson.databind.exc.InvalidNullException;
import com.tutorial.bikesservice.entity.Bike;
import com.tutorial.bikesservice.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BikeService {

    @Autowired
    private BikeRepository bikeRepository;

    public List<Bike> getAll() {
        return bikeRepository.findAll();
    }

    public Bike getById(int id) {
        return bikeRepository.findById(id).orElse(null);
    }

    public boolean existsBike(int id) {
        return bikeRepository.existsById(id);
    }

    public Bike create(Bike bike) {
        return bikeRepository.save(bike);
    }

    public Bike update(Bike bike) {
        Bike oldBike = bikeRepository.findById(bike.getId()).orElse(new Bike());

        oldBike.setBrand(bike.getBrand());
        oldBike.setModel(bike.getModel());
        oldBike.setUserId(bike.getUserId());

        return bikeRepository.save(oldBike);
    }

    public void deleteById(int id) {
        bikeRepository.deleteById(id);
    }

    public List<Bike> getByUserId(int userId) {
        return bikeRepository.findByUserId(userId);
    }

}
