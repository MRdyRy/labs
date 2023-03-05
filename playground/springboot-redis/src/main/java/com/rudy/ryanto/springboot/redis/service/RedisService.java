package com.rudy.ryanto.springboot.redis.service;

import com.rudy.ryanto.springboot.redis.domain.Food;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    private final String CACHE_NAME = "sample";
    private final String HASH_KEY_CACHE = "sample_hash_key";

    public void putCache(Food food){
        try {
            log.info("put to redis with hash operation");
            redisTemplate.opsForHash().put(CACHE_NAME,HASH_KEY_CACHE,food);
            log.info("put to redis cache with value operation and duration!");
            redisTemplate.opsForValue().set(CACHE_NAME,food, Duration.ofMinutes(1));
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public Food getCache(String key) {
        try{
            log.info("get from cache : {}",key);
            return (Food) redisTemplate.opsForValue().get(key);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public Boolean deleteCache(String key) {
        try{
            var isDelete = redisTemplate.opsForValue().getOperations().delete(key);
            log.info("delete Cache : {} {}",key,isDelete);
            return isDelete;
        }catch (Exception e){
            log.error("failed caused : ",e);
            return false;
        }
    }
}
