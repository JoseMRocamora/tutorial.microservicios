package com.tutorial.usersservice.repository;

import com.tutorial.usersservice.model.Bike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
public class BikeRepositoryImpl implements BikeRepository{

    private static final String URL_BIKES_BY_USER = "http://localhost:8003/bikes/by-user/%d";

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Bike> getBikesByUserId(int userId) {
        List<Bike> bikes = restTemplate.getForObject(String.format(URL_BIKES_BY_USER, userId), List.class);
        return bikes;
    }

}
