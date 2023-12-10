package com.rudy.ryanto.actuator.controller;

import com.rudy.ryanto.actuator.domain.Food;
import com.rudy.ryanto.actuator.service.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class FoodController {

    @Autowired
    private FoodService foodService;

    @PostMapping("/v1/food/save")
    public ResponseEntity<?> saveFood(@RequestBody Food food){
        return ResponseEntity.ok(foodService.saveFood2(food));
    }

    @PostMapping("/v1/food/save2")
    public ResponseEntity<?> saveFood2(){
        log.info("/v1/food/save2");
        return ResponseEntity.ok(foodService.saveDummyFood(1009000));
    }

    @PostMapping("/v1/food/save3")
    public ResponseEntity<?> saveFood3(){
        log.info("/v1/food/save3");
        return ResponseEntity.ok(foodService.saveDummyFood(199999));
    }
}
