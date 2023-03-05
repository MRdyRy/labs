package com.rudy.ryanto.springboot.redis.controller;

import com.rudy.ryanto.springboot.redis.domain.Food;
import com.rudy.ryanto.springboot.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;
    @PostMapping("/put")
    public ResponseEntity<?> putDataToRedis(@RequestBody Food food){
        redisService.putCache(food);
        return ResponseEntity.ok("Success!");
    }


    @ResponseBody
    @GetMapping(value = "/get/{key}")
    public Food getFromRedis(@PathVariable String key){
        return redisService.getCache(key);
    }

    @ResponseBody
    @GetMapping(value = "/del/{key}")
    public ResponseEntity<?> deleteFromRedis(@PathVariable String key){
        var isDeleted = redisService.deleteCache(key);
        return ResponseEntity.ok(isDeleted? "success delete ":"Failed to delete");
    }



}