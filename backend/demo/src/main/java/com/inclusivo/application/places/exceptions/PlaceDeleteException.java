package com.inclusivo.application.places.exceptions;

public class PlaceDeleteException extends RuntimeException{
    public PlaceDeleteException(Long id) {
        super("Error al eliminar el lugar con id " + id);
    }
}
