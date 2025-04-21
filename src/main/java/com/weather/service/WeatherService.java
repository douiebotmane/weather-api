package com.weather.service;

import com.weather.dto.CurrentWeatherDto;
import com.weather.dto.ForecastSummaryDto;
import com.weather.exception.WeatherException;
import com.weather.mapper.WeatherMapper;
import com.weather.model.*;
import com.weather.remote.WeatherBitApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

import static com.weather.consants.WeatherConstants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherService {

    private final WeatherBitApi weatherBitApi;
    private final WeatherMapper mapper;

    @Cacheable("currentWeather")
    public CurrentWeatherDto getCurrentWeather(String city) {
        try {
            WeatherBitCurrentResponse response = weatherBitApi.getCurrentWeather(city);
            if (Objects.isNull(response) || CollectionUtils.isEmpty(response.getData())) {
                throw new WeatherException(WeatherException.ErrorCode.CITY_NOT_FOUND);
            }
            WeatherBitCurrent currentWeather = response.getData().getFirst();
            return mapper.toCurrentWeatherDto(currentWeather);
        } catch (Exception e) {
            log.error(WeatherException.ErrorCode.API_CALL_FAILED.getMessage());
            throw new WeatherException(WeatherException.ErrorCode.API_CALL_FAILED);
        }
    }

    @Cacheable("forecastSummary")
    public ForecastSummaryDto getForecastSummary(String city) {
        try {
            WeatherBitForecastResponse response = weatherBitApi.getForecastWeather(city, 7);
            if (Objects.isNull(response) || CollectionUtils.isEmpty(response.getData())) {
                throw new WeatherException(WeatherException.ErrorCode.CITY_NOT_FOUND);
            }

            List<WeatherBitForecast> forecasts = response.getData();

            List<Double> temps = forecasts.stream().map(WeatherBitForecast::getTemp).toList();
            List<Double> pressures = forecasts.stream().map(WeatherBitForecast::getPres).toList();
            List<Double> winds = forecasts.stream().map(f -> f.getWindSpeed() * 3.6).toList();

            String temperatureTrend = calculateTemperatureTrend(temps);
            String pressureTrend = calculatePressureTrend(pressures);
            String generalEvolution = determineGeneralEvolution(temperatureTrend, pressureTrend);
            String classifiedWind = classifyWind(winds.stream().mapToDouble(d -> d).average().orElse(0.0));

            return mapper.toForecastSummaryDto(generalEvolution, temperatureTrend, pressureTrend, classifiedWind);
        } catch (Exception e) {
            log.error(WeatherException.ErrorCode.API_CALL_FAILED.getMessage());
            throw new WeatherException(WeatherException.ErrorCode.API_CALL_FAILED);
        }
    }

    private String calculateTemperatureTrend(List<Double> values) {
        double diff = values.getLast() - values.getFirst();
        if (diff > 1.5) return INCREASING;
        if (diff < -1.5) return DECREASING;
        return STABLE;
    }

    private String calculatePressureTrend(List<Double> values) {
        double diff = values.getLast() - values.getFirst();
        if (diff > 5) return SHARPLY_INCREASING;
        if (diff > 2) return INCREASING;
        if (diff < -5) return SHARPLY_DECREASING;
        if (diff < -2) return DECREASING;
        return STABLE;
    }

    private String determineGeneralEvolution(String tempTrend, String pressureTrend) {
        if ((INCREASING.equals(tempTrend) || STABLE.equals(tempTrend)) &&
                (INCREASING.equals(pressureTrend) || SHARPLY_INCREASING.equals(pressureTrend))) {
            return IMPROVING;
        }
        if (DECREASING.equals(tempTrend) && (DECREASING.equals(pressureTrend) || SHARPLY_DECREASING.equals(pressureTrend))) {
            return DETERIORATING;
        }
        return STABLE;
    }

    private String classifyWind(double speed) {
        if (speed < 1) return CALM;
        else if (speed <= 5) return LIGHT_AIR;
        else if (speed <= 11) return LIGHT_BREEZE;
        else if (speed <= 19) return GENTLE_BREEZE;
        else if (speed <= 28) return MODERATE_BREEZE;
        else if (speed <= 38) return FRESH_BREEZE;
        else if (speed <= 49) return STRONG_BREEZE;
        else if (speed <= 61) return NEAR_GALE;
        else if (speed <= 74) return GALE;
        else if (speed <= 88) return STRONG_GALE;
        else if (speed <= 102) return STORM;
        else if (speed <= 117) return VIOLENT_STORM;
        else return HURRICANE;
    }
}
