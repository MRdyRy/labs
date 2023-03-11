package com.rudy.ryanto.springboot.amq.listener;

import com.rudy.ryanto.springboot.amq.domain.Food;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ListenerAMQImpl implements ListenerAMQ{

    @Value("${DESTINATION_QUEUE}")
    private String destination;

    @JmsListener(destination = "${DESTINATION_QUEUE}",
            containerFactory = "jmsListenerContainerFactory", subscription = "sub1")
    @Override
    public <T extends Food> Food getFood(T t) {
        log.info("recevie messsage : {}",t);
        return t;
    }

}
