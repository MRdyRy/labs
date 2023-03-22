package com.rudy.ryanto.springboot.jpa.service;

import com.rudy.ryanto.springboot.jpa.domain.Food;
import com.rudy.ryanto.springboot.jpa.repository.FoodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;
    public Object doSave(Food food) {
        try{
            doPrintLog("save",food);
            var foodResult = foodRepository.save(food);
            return foodResult;
        }catch (Exception e){
            throw e;
        }
    }

    public <T extends Food> Food doUpdate(T food) {
        try {
            doPrintLog("update",food);
            var foodCheck = foodRepository.findById(food.getIdFood());
            if(foodCheck.isPresent()){
                var foodMap = mapExistingWithUpdateValue(foodCheck.get(), food);
                return foodRepository.saveAndFlush(foodMap);
            }else{
                return null;
            }
        }catch (Exception e){
            throw e;
        }
    }

    private <T extends Food> Food mapExistingWithUpdateValue(Food foodExist, Food foodUpdate) {
        doPrintLog("map",foodUpdate);
        foodExist.setFoodName(foodUpdate.getFoodName());
        foodExist.setPrice(foodUpdate.getPrice());
        foodExist.setUpdateBy(foodUpdate.getUpdateBy());
        foodExist.setUpdateDate(foodUpdate.getUpdateDate());
        return foodExist;
    }

    public String doDelete(String id) {
        try {
            doPrintLog("delete",id);
            var foodDelete = foodRepository.findById(Long.parseLong(id));
            if(foodDelete.isPresent()){
                foodRepository.deleteById(Long.parseLong(id));
                return "Food with id =".concat(id).concat("has been deleted!");
            }else
                return "Food with id =".concat(id).concat("not exist!");
        }catch (Exception e){
            throw e;
        }
    }

    private <T extends Object>void doPrintLog(String e, T o) {
        log.debug("processing : ["+e+"] for object : {}",o);
    }
}
