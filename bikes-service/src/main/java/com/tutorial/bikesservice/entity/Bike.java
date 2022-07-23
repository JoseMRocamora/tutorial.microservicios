package com.tutorial.bikesservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="BIKES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String brand;
    private String model;
    private int userId;

}
