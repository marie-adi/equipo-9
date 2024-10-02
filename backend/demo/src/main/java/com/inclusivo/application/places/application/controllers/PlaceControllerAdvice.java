package com.inclusivo.application.places.application.controllers;

import com.inclusivo.application.places.domain.PlaceApiError;
import com.inclusivo.application.places.exceptions.PlaceDeleteException;
import com.inclusivo.application.places.exceptions.PlaceNotFoundException;
import com.inclusivo.application.places.exceptions.PlaceSaveException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class PlaceControllerAdvice {

    @ExceptionHandler(PlaceNotFoundException.class)
    public ResponseEntity<PlaceApiError> handlePlaceNotFoundException(PlaceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(PlaceApiError.builder()
                                                .status(HttpStatus.FORBIDDEN)
                                                .dateTime(LocalDateTime.now())
                                                .message(e.getMessage())
                                                .build());
    }

    @ExceptionHandler(PlaceDeleteException.class)
    public ResponseEntity<PlaceApiError> handlePlaceDeleteException(PlaceDeleteException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(PlaceApiError
                        .builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .dateTime(LocalDateTime.now())
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(PlaceSaveException.class)
    public ResponseEntity<PlaceApiError> handlePlaceSaveException(PlaceSaveException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(PlaceApiError
                                     .builder()
                                     .status(HttpStatus.NOT_MODIFIED)
                                     .dateTime(LocalDateTime.now())
                                     .message(e.getMessage())
                                     .build());
    }
}
