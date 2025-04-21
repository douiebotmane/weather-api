package com.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherBitForecast {
    private double temp;
    private double pres;
    @JsonProperty("wind_spd")
    private double windSpeed;
}
