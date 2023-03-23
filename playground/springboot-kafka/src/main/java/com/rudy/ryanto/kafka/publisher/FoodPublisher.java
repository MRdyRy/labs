package com.rudy.ryanto.kafka.publisher;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
public class FoodPublisher {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Value("FOOD_TOPIC:favorite-food")
    private String foodTopic;

    public <T> boolean sendRecomendedFood(T food){
        log.info("sending recomended food : {}",food);
        var res = false;
        try{
            ListenableFuture<SendResult<String, String>> future =
                    kafkaTemplate.send(foodTopic, food);

            future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

                @Override
                public void onSuccess(SendResult<String, String> result) {
                    log.info("Sent message=["+food+"] with offset=["+result.getRecordMetadata().offset()+"]");
                    validateResult(true,res);

                }
                @Override
                public void onFailure(Throwable ex) {
                    log.info("Unable to send message=["+food+"] due to : "+ex.getMessage());
                    validateResult(false,res);
                }
            });
        }catch (Exception e){
            throw e;
        }
        return res;
    }

    private void validateResult(boolean b, boolean r) {
        r = b;
    }
}
