package com.inclusivo.application.places.application.facade;

import com.inclusivo.application.places.domain.PlaceModel;

import java.util.List;
import java.util.Optional;

public class PlaceFacadeImpl implements PlaceFacade {

    @Override
    public List<PlaceModel> findAll() {

        return List.of();
    }

    @Override
    public Optional<PlaceModel> findById(Long id) {

        return Optional.empty();
    }

    @Override
    public PlaceModel save(PlaceModel place) {

        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<PlaceModel> findByCity(String country) {

        return List.of();
    }

    @Override
    public Optional<PlaceModel> findByNameContainingIgnoreCase(String name) {

        return Optional.empty();
    }
}
