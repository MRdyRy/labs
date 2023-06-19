package com.rudy.ryanto.actuator.controller;

import com.rudy.ryanto.actuator.domain.Food;
import com.rudy.ryanto.actuator.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @PostMapping("/v1/food/save")
    public ResponseEntity<?> saveFood(@RequestBody Food food){
        return ResponseEntity.ok(foodService.saveFood2(food));
    }

    @PostMapping("/v1/food/save2")
    public ResponseEntity<?> saveFood2(){
        return ResponseEntity.ok(foodService.saveDummyFood());
    }
}
