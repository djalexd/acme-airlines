package com.github.airlines.model.exceptions;

/**
 * Can be thrown by service methods that need to signal that no airport
 * was found, by the given query (by name, etc).
 *
 * @author alex.dobjanschi
 * @since 11/18/12 1:36 PM
 */
public class NoAirportFoundException extends RuntimeException {
    public NoAirportFoundException() {
    }

    public NoAirportFoundException(String message) {
        super(message);
    }

    public NoAirportFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
