package com.tutorial.usersservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bike {

    private int id;
    private String brand;
    private String model;
    private int userId;

}
