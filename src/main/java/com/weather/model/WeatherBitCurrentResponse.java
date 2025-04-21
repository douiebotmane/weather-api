package com.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherBitCurrentResponse {
    private List<WeatherBitCurrent> data;
}
