package com.weather.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class WeatherException extends RuntimeException {

    private final ErrorCode code;
    private final String message;

    public WeatherException(ErrorCode code) {
        super(code.getMessage());
        this.code = code;
        this.message = code.getMessage();
    }

    @RequiredArgsConstructor
    @Getter
    public enum ErrorCode {
        CITY_NOT_FOUND("City not found or API response is empty"),
        API_CALL_FAILED("Error while retrieving weather data");

        private final String message;
    }
}
