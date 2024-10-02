package com.inclusivo.application.places.application.services;

import com.inclusivo.application.places.domain.PlaceModel;

import java.util.List;
import java.util.Optional;

public interface PlaceService {

    List<PlaceModel> findAll();

    Optional<PlaceModel> findById(Long id);

    PlaceModel save(PlaceModel place);

    void deleteById(Long id);

    List<PlaceModel> findByCity(String country);

    Optional<PlaceModel> findByNameContainingIgnoreCase(String name);
}
