package com.tutorial.usersservice.repository;

import com.tutorial.usersservice.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
public class CarRepositoryImpl implements CarRepository {

    private static final String URL_CARS_BY_USER = "http://cars-service/cars/by-user/%d";

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Car> getCarsByUserId(int userId) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + jwt.getTokenValue());

        ResponseEntity<List> carsResponseEntity = restTemplate.exchange(String.format(URL_CARS_BY_USER, userId), HttpMethod.GET, new HttpEntity<>(httpHeaders), List.class);
        return carsResponseEntity.getBody();
    }

}
