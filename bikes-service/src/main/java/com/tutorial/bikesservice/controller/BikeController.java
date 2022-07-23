package com.tutorial.bikesservice.controller;

import com.tutorial.bikesservice.entity.Bike;
import com.tutorial.bikesservice.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bikes")
public class BikeController {

    @Autowired
    BikeService bikeService;

    @PostMapping
    public ResponseEntity<Bike> create(@RequestBody Bike bike) {
        Bike createdBike = bikeService.create(bike);

        return new ResponseEntity(createdBike, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Bike> update(@RequestBody Bike bike) {
        boolean existsBike = bikeService.existsBike(bike.getId());
        Bike updatedBike = bikeService.update(bike);

        if (existsBike) {
            return ResponseEntity.noContent().build();
        }
        else {
            return new ResponseEntity(updatedBike, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bike> updateById(@PathVariable int id, @RequestBody Bike bike) {
        if (!bikeService.existsBike(id)) {
            return ResponseEntity.notFound().build();
        }

        bike.setId(id);
        Bike updatedBike = bikeService.update(bike);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Bike> deleteById(@PathVariable int id) {
        bikeService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Bike>> getAll() {
        List<Bike> bikes = bikeService.getAll();

        if (bikes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bikes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getById(@PathVariable int id) {
        if (!bikeService.existsBike(id)) {
            return ResponseEntity.notFound().build();
        }

        Bike bike = bikeService.getById(id);
        return ResponseEntity.ok(bike);
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<Bike>> getByUserId(@PathVariable int userId) {
        List<Bike> bikes = bikeService.getByUserId(userId);

        if (bikes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bikes);
    }

}
