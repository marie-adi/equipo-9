package com.inclusivo.application.places.exceptions;

public class PlaceNotFoundException extends RuntimeException{
    public PlaceNotFoundException(String message) {
        super(message);
    }
}
