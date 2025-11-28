package com.business_crab.weather_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {
    private String country;
    private String temperature;
    private String description;
    private String dateTime;
}