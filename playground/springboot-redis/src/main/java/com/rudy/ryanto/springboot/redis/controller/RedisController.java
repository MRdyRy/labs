package com.rudy.ryanto.springboot.redis.controller;

import com.rudy.ryanto.springboot.redis.domain.Food;
import com.rudy.ryanto.springboot.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;
    @PostMapping("/searchbox")
    public ResponseEntity<?> putDataToRedis(@RequestBody Food food){
        redisService.putCache(food);
        return ResponseEntity.ok("Success!");
    }
}