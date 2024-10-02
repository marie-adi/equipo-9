package com.inclusivo.application.places.infraestructure.repository;

import com.inclusivo.application.places.domain.PlaceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlaceRepository extends JpaRepository<PlaceModel, Long> {

    List<PlaceModel> findByCity(String country);
}
