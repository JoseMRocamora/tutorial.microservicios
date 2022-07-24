package com.tutorial.usersservice.repository;

import com.tutorial.usersservice.model.Bike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="bikes-service")
public interface BikeFeignClient {

    @PostMapping("/bikes")
    Bike create(@RequestBody Bike bike);

}
