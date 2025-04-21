package com.weather.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "weatherbit")
@Getter
@Setter
public class WeatherBitConfig {

    private String apiKey;
    private String baseUrl;
}
