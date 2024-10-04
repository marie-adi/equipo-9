package com.inclusivo.application.places.application.controllers;

import com.inclusivo.application.places.application.facade.PlaceFacade;
import com.inclusivo.application.places.domain.PlaceDTO;
import com.inclusivo.application.places.exceptions.PlaceNotFoundException;
import com.inclusivo.application.places.infraestructure.repository.PlaceRepository;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/places")
public class PlaceController {

    @Resource
    private PlaceFacade placeFacade;

    @Resource
    private PlaceRepository placeRepository;

    @GetMapping
    public ResponseEntity<List<PlaceDTO>> findAll() {
        List<PlaceDTO> listPlaces = placeFacade.findAll();
        return Optional.of(listPlaces)
                .filter(places -> !places.isEmpty())
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new PlaceNotFoundException("La lista se encuentra vacía"));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PlaceDTO> findById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(placeFacade.findById((long) id));
    }

    @PostMapping
    public ResponseEntity<PlaceDTO> save(@RequestBody PlaceDTO placeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(placeFacade.save(placeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable int id) {
        try {
            placeRepository.deleteById((long) id);
            return ResponseEntity.status(HttpStatus.OK).body(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<PlaceDTO>> findByCity(@PathVariable String city) {
        List<PlaceDTO> listPlaces = placeFacade.findAll();
        return Optional.of(listPlaces)
                .filter(places -> !places.isEmpty())
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new PlaceNotFoundException("No se han encontrado resultados con la ciudad " + city));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PlaceDTO> findByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(placeFacade.findByNameContainingIgnoreCase(name));
    }
}
