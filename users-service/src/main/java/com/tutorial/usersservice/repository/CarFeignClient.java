package com.tutorial.usersservice.repository;

import com.tutorial.usersservice.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="cars-service", url="http://localhost:8002/cars")
public interface CarFeignClient {

    @PostMapping
    public Car create(@RequestBody Car car);

}
