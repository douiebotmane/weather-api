package com.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentWeatherDto {
    private String description;
    private double temperature;
    private double windSpeed;
    private double humidity;
}
