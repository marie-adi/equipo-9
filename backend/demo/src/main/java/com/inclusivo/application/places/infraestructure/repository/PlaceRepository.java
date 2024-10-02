package com.inclusivo.application.places.infraestructure.repository;

import com.inclusivo.application.places.domain.PlaceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<PlaceModel, Long> {

    List<PlaceModel> findByCity(String country);

    Optional<PlaceModel> findByNameContainingIgnoreCase(String id);
}
