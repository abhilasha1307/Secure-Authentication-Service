package com.abhilasha.auth.exception;

import java.time.Instant;

public class ApiError {

    private final String message;
    private final String errorCode;
    private final Instant timestamp;

    public ApiError(String message) {
        this(message, null);
    }

    public ApiError(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
        this.timestamp = Instant.now();
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
