package com.inclusivo.application.places.application.services;

import com.inclusivo.application.places.domain.PlaceModel;
import com.inclusivo.application.places.exceptions.PlaceDeleteException;
import com.inclusivo.application.places.exceptions.PlaceNotFoundException;
import com.inclusivo.application.places.infraestructure.repository.PlaceRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceServiceImpl implements PlaceService{

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
        placeRepository.findById(id)
                .map(placeModel -> {
                    placeRepository.delete(placeModel);
                    return placeModel;
                })
                .orElseThrow(() -> new PlaceDeleteException(id));
    }

    @Override
    public List<PlaceModel> findByCity(String city) {

        Assert.notNull(city, "City value is null.");
        return placeRepository.findByCity(city);
    }

    @Override
    public Optional<PlaceModel> findByNameContainingIgnoreCase(String name) {

        Assert.notNull(name, "Name value is null.");
        return placeRepository.findByNameContainingIgnoreCase(name);
    }
}
