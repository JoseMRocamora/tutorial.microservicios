package com.tutorial.usersservice.repository;

import com.tutorial.usersservice.model.Bike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="bikes-service", url="http://localhost:8003/bikes")
public interface BikeFeignClient {

    @PostMapping
    public Bike create(@RequestBody Bike bike);

}
