package com.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherBitCurrent {
    private double temp;
    @JsonProperty("wind_spd")
    private double windSpeed;
    private double rh;
    private WeatherDesc weather;
}
