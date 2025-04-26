package com.weather.remote;

import com.weather.config.WeatherBitRemoteConfig;
import com.weather.model.WeatherBitCurrentResponse;
import com.weather.model.WeatherBitForecastResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "WeatherBitApi",
        url = "${weatherbit.baseUrl}",
        configuration = WeatherBitRemoteConfig.class
)
public interface WeatherBitApi {

    @GetMapping("/current")
    WeatherBitCurrentResponse getCurrentWeather(@RequestParam("city") String city);

    @GetMapping("/forecast/daily")
    WeatherBitForecastResponse getForecastWeather(@RequestParam("city") String city, @RequestParam("days") int days);
}
