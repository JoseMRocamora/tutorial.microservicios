package com.tutorial.usersservice.repository;

import com.tutorial.usersservice.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="cars-service")
public interface CarFeignClient {

    @PostMapping("/cars")
    Car create(@RequestBody Car car);

}
