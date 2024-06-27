package org.example.exception;

public class SlotValidationException extends RuntimeException {
    public SlotValidationException(String message) {
        super(message);
    }
}