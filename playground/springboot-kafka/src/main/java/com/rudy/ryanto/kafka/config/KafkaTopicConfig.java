package com.rudy.ryanto.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Configuration
public class KafkaTopicConfig {

    @Value("${listoftopic}")
    private String listTopic;

    final SingletonBeanRegistry beanRegistry;

    public KafkaTopicConfig(GenericApplicationContext context) {
        this.beanRegistry = context.getBeanFactory();
    }

    @PostConstruct
    public void createTopic(){
        if(!StringUtils.isEmpty(listTopic)){
            Arrays.stream(listTopic.split(",")).forEach(topic->{
                NewTopic newTopic = TopicBuilder.name(topic).partitions(3).replicas(3).build();
                beanRegistry.registerSingleton(topic,newTopic);
            });
        }
    }
}