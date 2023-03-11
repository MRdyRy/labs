package com.rudy.ryanto.springboot.amq.publisher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Slf4j
@Component
public class PublisherAMQImpl implements PublisherAMQ{

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${DESTINATION_QUEUE}")
    private String destination;
    @Override
    public <T> void sendData(T data) {
        try{
            log.info("Send data to topic : {}",destination);
            jmsTemplate.convertAndSend(destination,data);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public <T> Boolean sendDataWithCallBack(T t) throws JMSException {
        try {
            log.info("send data to topic : {}", destination);
            var result = jmsTemplate.sendAndReceive(destination, session -> (Message) t);
            log.info("receive callback : {}",result);
            if(null!=result.getJMSMessageID())
                return true;
            else
                return false;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
