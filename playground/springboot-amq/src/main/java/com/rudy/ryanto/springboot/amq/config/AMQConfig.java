package com.rudy.ryanto.springboot.amq.config;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.JMSException;

@Configuration
public class AMQConfig {

    @Value("${CLIENT_ID}")
    private String clientId;

    @Value("${DURABLE}")
    private boolean durable;

    @Value("${BROKER_URL}")
    private String brokerUrl;

    @Value("${AMQ_USER")
    private String user;

    @Value("${AMQ_PASSWORD}")
    private String password;

    @Value("${GROUP_AMQ}")
    private String group;

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerUrl);
        activeMQConnectionFactory.setUser(user);
        activeMQConnectionFactory.setPassword(password);
        activeMQConnectionFactory.setGroupID(group);
        return activeMQConnectionFactory;
    }

    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerFactory() throws JMSException {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(true);
        factory.setSubscriptionDurable(durable);
        factory.setClientId(clientId);
        factory.setConnectionFactory(activeMQConnectionFactory());
//        factory.setConcurrency("10-20");
        return factory;
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory() throws JMSException {
        return new CachingConnectionFactory(activeMQConnectionFactory());
    }

    @Bean
    public JmsTemplate jmsTemplate() throws JMSException {
        return new JmsTemplate(cachingConnectionFactory());
    }
}
