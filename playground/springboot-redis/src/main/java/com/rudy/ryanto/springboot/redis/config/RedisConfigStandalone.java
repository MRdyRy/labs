package com.rudy.ryanto.springboot.redis.config;

import io.lettuce.core.ClientOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfigStandalone {
    @Value("${redis.host}")
    private String host;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.port}")
    private int port;

    @Bean
    public ClientOptions clientOptions() {
        return ClientOptions.builder()
                .disconnectedBehavior(ClientOptions.DisconnectedBehavior.REJECT_COMMANDS)
                .autoReconnect(true)
                .build();
    }

    @Bean
    public RedisConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host, port);
        if(!password.isEmpty()){
            redisStandaloneConfiguration.setPassword(password);
        }

        LettuceClientConfiguration configuration = LettuceClientConfiguration.builder()
                .clientOptions(clientOptions()).build();

        return new LettuceConnectionFactory(redisStandaloneConfiguration, configuration);
    }

    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(connectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
