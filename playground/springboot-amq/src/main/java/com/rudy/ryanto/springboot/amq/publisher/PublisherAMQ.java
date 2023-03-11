package com.rudy.ryanto.springboot.amq.publisher;

import com.rudy.ryanto.springboot.amq.domain.Food;

import javax.jms.JMSException;
import javax.websocket.SendResult;

public interface PublisherAMQ {

    <T> void sendData(T data);

    <T> Boolean sendDataWithCallBack(T t) throws JMSException;
}
