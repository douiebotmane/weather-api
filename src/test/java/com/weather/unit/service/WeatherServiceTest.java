package com.weather.unit.service;

import com.weather.dto.CurrentWeatherDto;
import com.weather.exception.WeatherException;
import com.weather.mapper.WeatherMapper;
import com.weather.model.WeatherBitCurrent;
import com.weather.model.WeatherBitCurrentResponse;
import com.weather.model.WeatherDesc;
import com.weather.remote.WeatherBitApi;
import com.weather.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {

    @Mock
    private WeatherBitApi weatherBitApi;

    @Mock
    private WeatherMapper mapper;

    @InjectMocks
    private WeatherService weatherService;

    @ParameterizedTest
    @CsvSource({"Toulouse,25.0,10.0,60,Sunny", "Paris,30.0,25.0,55,Cloudy"})
    void testGetCurrentWeather_OK(String city, double temp, double windSpeed, double humidity, String description) {
        // Given
        WeatherBitCurrent current = new WeatherBitCurrent();
        current.setTemp(temp);
        current.setWindSpeed(windSpeed);
        current.setRh(humidity);
        WeatherDesc wd = new WeatherDesc();
        wd.setDescription(description);
        current.setWeather(wd);

        WeatherBitCurrentResponse response = new WeatherBitCurrentResponse();
        response.setData(List.of(current));

        CurrentWeatherDto expectedCurrentWeather = new CurrentWeatherDto(description, temp, windSpeed, humidity);
        when(weatherBitApi.getCurrentWeather(anyString())).thenReturn(response);
        when(mapper.toCurrentWeatherDto(current)).thenReturn(expectedCurrentWeather);

        // When
        CurrentWeatherDto result = weatherService.getCurrentWeather(city);

        // Then
        assertEquals(expectedCurrentWeather, result);
    }

    @Test
    void testGetCurrentWeather_throwsException() {
        // Wen
        when(weatherBitApi.getCurrentWeather(anyString())).thenThrow(new RuntimeException());

        // Then
        assertThrows(WeatherException.class, () -> weatherService.getCurrentWeather("test"));
    }
}
