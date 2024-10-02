package com.inclusivo.application.places.application.facade;

import com.inclusivo.application.places.application.converters.ConvertToPlaceDTO;
import com.inclusivo.application.places.application.converters.ConvertToPlaceModel;
import com.inclusivo.application.places.application.services.PlaceService;
import com.inclusivo.application.places.domain.PlaceDTO;
import com.inclusivo.application.places.exceptions.PlaceNotFoundException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class PlaceFacadeImpl implements PlaceFacade {

    @Resource
    private PlaceService placeService;

    @Resource
    private ConvertToPlaceDTO convertToPlaceDTO;

    @Resource
    private ConvertToPlaceModel convertToPlaceModel;

    @Override
    public List<PlaceDTO> findAll() {
        return placeService.findAll().stream().map(convertToPlaceDTO::convert).toList();
    }

    @Override
    public PlaceDTO findById(Long id) {
        return placeService.findById(id)
                .map(convertToPlaceDTO::convert)
                .orElseThrow(() -> new PlaceNotFoundException("No se han encontrado coincidencias con el id " + id));
    }

    @Override
    public PlaceDTO save(PlaceDTO place) {
        return convertToPlaceDTO.convert(placeService.save(convertToPlaceModel.convert(place)));
    }

    @Override
    public void deleteById(Long id) {
        placeService.deleteById(id);
    }

    @Override
    public List<PlaceDTO> findByCity(String country) {
        return placeService.findByCity(country).stream().map(convertToPlaceDTO::convert).toList();
    }

    @Override
    public PlaceDTO findByNameContainingIgnoreCase(String name) {
        return Optional.of(placeService.findByNameContainingIgnoreCase(name).map(convertToPlaceDTO::convert)).get()
                .orElseThrow(() -> new PlaceNotFoundException("No se han encontrado coincidencias con el nombre " + name));
    }
}
