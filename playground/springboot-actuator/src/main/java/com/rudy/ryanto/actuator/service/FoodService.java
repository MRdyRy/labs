package com.rudy.ryanto.actuator.service;

import com.rudy.ryanto.actuator.domain.Food;
import com.rudy.ryanto.actuator.repository.FoodRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public List<Food> saveDummyFood(){
        List<Food> foodList = new ArrayList<>();
        try {
            for (int i =0; i<10000;i++){
                Food food = new Food();
                food.setFoodName(UUID.randomUUID().toString());
                food.setPrice(i);
                foodList.add(food);
            }
            foodRepository.saveAll(foodList);
        }catch (Exception e){
            throw e;
        }
        return foodList;
    }
}