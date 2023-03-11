package com.rudy.ryanto.springboot.amq.controller;


import com.rudy.ryanto.springboot.amq.domain.Food;
import com.rudy.ryanto.springboot.amq.publisher.PublisherAMQImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;

@RestController
@RequestMapping("/api")
public class FoodController {

    @Autowired
    PublisherAMQImpl publisherAMQImp;

    @PostMapping(value = "/v1/submit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> submitFood2(@RequestBody Food food){
        publisherAMQImp.sendData(food);
        return ResponseEntity.ok(food);
    }

    @PostMapping(value = "/v2/submit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> submitFood(@RequestBody Food food) throws JMSException {
        var result = publisherAMQImp.sendDataWithCallBack(food);
        return ResponseEntity.ok(result?"Success send data to AMQ !":"Failed to send data!");
    }

}
