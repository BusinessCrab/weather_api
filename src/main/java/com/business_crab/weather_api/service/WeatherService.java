package com.business_crab.weather_api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.business_crab.weather_api.model.Weather;

@Service
public class WeatherService {
    private static final String API_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/tangerang?unitGroup=metric&key=968QQR7C5UCK9ZTNAT294PUCX&contentType=json";
    
    private final RedisTemplate<String , Weather> redisTemplate;
    private final RedisService redisService;

    public WeatherService(final RedisTemplate<String , Weather> redisTemplate ,
                          final RedisService redisService)
    {
        this.redisTemplate = redisTemplate;
        this.redisService = redisService;
    }

    public Weather getWeather() {
        final RestTemplate restTemplate = new RestTemplate();
        final Map<String , Object> response = (Map<String , Object>) restTemplate.getForObject(API_URL , Map.class);
        if (response != null && response.containsKey("days")) {
            List<Map<String , Object>> days = (List<Map<String , Object>>) response.get("days");
            List<Weather> weathers = new ArrayList<>();
            for (final Map<String , Object> dayWeather : days) {
                final String country = dayWeather.get("country").toString();
                final String temperature = dayWeather.get("temperature").toString();
                final String desription = dayWeather.get("description").toString();
                final String dateTime = dayWeather.get("dateTime").toString();

                Weather weather = new Weather(country , temperature , desription , dateTime);
                weathers.add(weather);
            }
            redisService.saveWeather("Weather", weathers.get(0));
            return (Weather) weathers.get(0);
        }
        return null;
    }

    public Weather getWeatherFromRedis() {
        return redisTemplate.opsForValue().get("Weather");
    }
}
/*    private String country;
    private String temperature;
    private String description;
    private String dateTime; */