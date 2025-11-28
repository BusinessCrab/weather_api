package com.business_crab.weather_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.business_crab.weather_api.model.Weather;
import com.business_crab.weather_api.service.WeatherService;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {
    private final WeatherService weatherService;
    public WeatherController(final WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/current")
    public ResponseEntity<Weather> getCurrentWeather() {
        Weather weather = weatherService.getWeather();
        if (weather != null) {
            return new ResponseEntity<>(weather , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/data")
    public ResponseEntity<Weather> getWeatherData() {
        Weather weather = weatherService.getWeatherFromRedis();
        if (weather != null) {
            return new ResponseEntity<>(weather , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}