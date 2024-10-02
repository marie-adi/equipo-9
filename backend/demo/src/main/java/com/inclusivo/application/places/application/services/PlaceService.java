package com.inclusivo.application.places.application.services;

import com.inclusivo.application.places.domain.PlaceModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PlaceService {

    List<PlaceModel> findAll();

    Optional<PlaceModel> findById(Long id);

    PlaceModel save(PlaceModel place);

    void deleteById(Long id);

    List<PlaceModel> findByCity(String country);

    Optional<PlaceModel> findByNameContainingIgnoreCase(String name);
}
