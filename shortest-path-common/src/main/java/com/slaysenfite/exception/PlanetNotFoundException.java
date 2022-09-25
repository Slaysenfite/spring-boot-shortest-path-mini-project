package com.slaysenfite.exception;

public class PlanetNotFoundException extends RuntimeException {
    public PlanetNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
