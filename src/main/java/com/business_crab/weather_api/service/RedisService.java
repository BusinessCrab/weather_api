package com.business_crab.weather_api.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.business_crab.weather_api.model.Weather;

@Service
public class RedisService {
    private final RedisTemplate<String , Weather> redisTemplate;
    public RedisService(final RedisTemplate<String , Weather> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    public void saveWeather(final String key , final Weather weather) {
        redisTemplate.opsForValue().set(key , weather);
    }
}