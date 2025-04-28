package com.weather.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class LimitExceededException extends RuntimeException {

    private final ErrorCode code;
    private final String message;

    public LimitExceededException(ErrorCode code) {
        this.code = code;
        this.message = code.getMessage();
    }

    @RequiredArgsConstructor
    @Getter
    public enum ErrorCode {
        RATE_LIMIT_EXCEEDED("Too many requests, try again later");

        private final String message;
    }
}
