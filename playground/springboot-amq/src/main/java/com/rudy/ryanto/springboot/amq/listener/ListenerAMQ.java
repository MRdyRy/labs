package com.rudy.ryanto.springboot.amq.listener;

import com.rudy.ryanto.springboot.amq.domain.Food;

public interface ListenerAMQ {
    <T extends Food> Food getFood(T data);
}
