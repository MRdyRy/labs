package com.rudy.ryanto.springboot.jpa.controller;

import com.rudy.ryanto.springboot.jpa.domain.Food;
import com.rudy.ryanto.springboot.jpa.service.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @PostMapping(value = "/v1/food/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveFood(@RequestBody Food food) {
        log.debug("save food : {}",food);
        return ResponseEntity.ok(foodService.doSave(food));
    }

    @PostMapping("/v1/food/update")
    public ResponseEntity<?> updateFood(@RequestBody Food food){
        log.debug("update food : {}",food);
        return ResponseEntity.ok(foodService.doUpdate(food));
    }


    @PostMapping("/v1/food/delete/{id}")
    public ResponseEntity<?> deleteFood(@PathVariable String id){
        log.debug("delete food : {}",id);
        return ResponseEntity.ok(foodService.doDelete(id));
    }

}
