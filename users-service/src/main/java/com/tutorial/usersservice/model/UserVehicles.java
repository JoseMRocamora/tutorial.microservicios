package com.tutorial.usersservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVehicles {

    private int id;
    private String name;
    private String email;
    private List<Car> cars;
    private List<Bike> bikes;

}
