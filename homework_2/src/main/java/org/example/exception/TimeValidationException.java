package org.example.exception;

public class TimeValidationException extends RuntimeException {
    public TimeValidationException(String message) {
        super(message);
    }
}