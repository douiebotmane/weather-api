package com.weather.controller;

import com.weather.dto.CurrentWeatherDto;
import com.weather.dto.ForecastSummaryDto;
import com.weather.logging.WeatherLog;
import com.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/current")
    @WeatherLog
    public CurrentWeatherDto getCurrentWeather(@RequestParam String location) {
        return weatherService.getCurrentWeather(location);
    }

    @GetMapping("/forecast")
    @WeatherLog
    public ForecastSummaryDto getForecastSummary(@RequestParam String location) {
        return weatherService.getForecastSummary(location);
    }
}
