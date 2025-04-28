package com.weather.controller;

import com.weather.config.ApiRateLimiter;
import com.weather.dto.CurrentWeatherDto;
import com.weather.dto.ForecastSummaryDto;
import com.weather.exception.LimitExceededException;
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
    private final ApiRateLimiter apiRateLimiter;

    @GetMapping("/current")
    @WeatherLog
    public CurrentWeatherDto getCurrentWeather(@RequestParam String location) {
        if (!apiRateLimiter.getBucket().tryConsume(1)) {
            throw new LimitExceededException(LimitExceededException.ErrorCode.RATE_LIMIT_EXCEEDED);
        }
        return weatherService.getCurrentWeather(location);
    }

    @GetMapping("/forecast")
    @WeatherLog
    public ForecastSummaryDto getForecastSummary(@RequestParam String location) {
        if (!apiRateLimiter.getBucket().tryConsume(1)) {
            throw new LimitExceededException(LimitExceededException.ErrorCode.RATE_LIMIT_EXCEEDED);
        }
        return weatherService.getForecastSummary(location);
    }
}
