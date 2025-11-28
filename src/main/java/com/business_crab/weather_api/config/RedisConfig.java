package com.business_crab.weather_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.business_crab.weather_api.component.WeatherSerializer;
import com.business_crab.weather_api.model.Weather;

import tools.jackson.databind.ObjectMapper;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String , Weather> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String , Weather> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new WeatherSerializer(new ObjectMapper()));
        return (RedisTemplate<String , Weather>) redisTemplate;
    }
}