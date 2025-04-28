package com.weather.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ratelimit")
@Getter
@Setter
public class RateLimitConfig {

    private Integer capacity;
    private Integer period;
}
