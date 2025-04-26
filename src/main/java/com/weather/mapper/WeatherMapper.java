package com.weather.mapper;

import com.weather.dto.CurrentWeatherDto;
import com.weather.dto.ForecastSummaryDto;
import com.weather.model.WeatherBitCurrent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WeatherMapper {

    @Mapping(source = "weather.description", target = "description")
    @Mapping(source = "temp", target = "temperature")
    @Mapping(expression = "java(current.getWindSpeed() * 3.6)", target = "windSpeed")
    @Mapping(source = "rh", target = "humidity")
    CurrentWeatherDto toCurrentWeatherDto(WeatherBitCurrent current);

    ForecastSummaryDto toForecastSummaryDto(String evolution, String tempTrend, String pressureTrend, String windCategory);
}
