package com.rudy.ryanto.kafka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rudy.ryanto.common.domain.FoodRecommend;
import com.rudy.ryanto.kafka.publisher.FoodPublisher;
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
    FoodPublisher foodPublisher;

    @PostMapping("/v1/publish/food")
    public ResponseEntity<?> publishFood(@RequestBody FoodRecommend foodRecommend) throws JsonProcessingException {
        foodPublisher.sendRecomendedFood(foodRecommend);
        return ResponseEntity.ok("publish message!!");
    }

}
