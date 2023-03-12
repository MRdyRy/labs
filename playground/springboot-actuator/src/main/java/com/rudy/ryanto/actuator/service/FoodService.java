package com.rudy.ryanto.actuator.service;

import com.rudy.ryanto.actuator.domain.Food;
import com.rudy.ryanto.actuator.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public void saveFood(Food food){
        foodRepository.save(food);
    }

    public Food saveFood2(Food f){
        try {
            return foodRepository.saveAndFlush(f);
        }catch (Exception e){
            throw e;
        }
    }
}