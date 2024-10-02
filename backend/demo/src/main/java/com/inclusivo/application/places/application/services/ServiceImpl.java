package com.inclusivo.application.places.application.services;

import com.inclusivo.application.places.domain.PlaceModel;
import com.inclusivo.application.places.infraestructure.repository.PlaceRepository;
import jakarta.annotation.Resource;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
public class ServiceImpl implements PlaceService{

    @Resource
    private PlaceRepository placeRepository;

    @Override
    public List<PlaceModel> findAll() {

        return placeRepository.findAll();
    }

    @Override
    public Optional<PlaceModel> findById(Long id) {

        Assert.notNull(id, "Id value is null.");
        return placeRepository.findById(id);
    }

    @Override
    public PlaceModel save(PlaceModel place) {

        Assert.notNull(place, "Place value is null.");
        return placeRepository.save(place);
    }

    @Override
    public void deleteById(Long id) {

        Assert.notNull(id, "Id value is null.");
        placeRepository.deleteById(id);
    }

    @Override
    public List<PlaceModel> findByCity(String country) {

        Assert.notNull(country, "Country value is null.");
        return List.of();
    }

    @Override
    public Optional<PlaceModel> findByNameContainingIgnoreCase(String name) {

        Assert.notNull(name, "Name value is null.");
        return Optional.empty();
    }
}
