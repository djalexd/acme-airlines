package com.github.airlines.model.exceptions;

/**
 * @author alex.dobjanschi
 * @since 11/21/12 12:58 AM
 */
public class NoAirplaneFoundException extends RuntimeException {
    public NoAirplaneFoundException() {
    }

    public NoAirplaneFoundException(String message) {
        super(message);
    }

    public NoAirplaneFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoAirplaneFoundException(Throwable cause) {
        super(cause);
    }
}
