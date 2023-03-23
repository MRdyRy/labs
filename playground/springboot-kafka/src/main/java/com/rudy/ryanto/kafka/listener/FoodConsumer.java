package com.rudy.ryanto.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FoodConsumer {
    @KafkaListener(topics = "favorite-food", groupId = "food")
    public void listenerFoodFavorite(String message){
        log.info("message receive : {}", message);
    }
}
