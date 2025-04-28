package com.weather.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Getter
@Configuration
public class ApiRateLimiter {

    private final Bucket bucket;
    private final RateLimitConfig rateLimitConfig;

    /**
     * Constructs the rate limiter with the specified capacity and refill duration.
     */
    public ApiRateLimiter(RateLimitConfig rateLimitConfig) {
        this.rateLimitConfig = rateLimitConfig;

        Bandwidth limit = Bandwidth.classic(rateLimitConfig.getCapacity(), Refill.greedy(rateLimitConfig.getCapacity(), Duration.ofHours(rateLimitConfig.getPeriod())));

        this.bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();
    }
}
