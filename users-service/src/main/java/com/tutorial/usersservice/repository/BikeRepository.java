package com.tutorial.usersservice.repository;

import com.tutorial.usersservice.model.Bike;

import java.util.List;

public interface BikeRepository {

    List<Bike> getBikesByUserId(int userId);

}
