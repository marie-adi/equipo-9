package com.inclusivo.application.places.application.facade;

import com.inclusivo.application.places.domain.PlaceDTO;
import java.util.List;
import java.util.Optional;

public interface PlaceFacade {
    List<PlaceDTO> findAll();

    PlaceDTO findById(Long id);

    PlaceDTO save(PlaceDTO place);

    void deleteById(Long id);

    List<PlaceDTO> findByCity(String country);

    PlaceDTO findByNameContainingIgnoreCase(String name);
}
