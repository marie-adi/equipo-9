package com.inclusivo.application.places.application.services;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertFalse;
import com.inclusivo.application.places.domain.PlaceModel;
import com.inclusivo.application.places.infraestructure.repository.PlaceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ServiceImplTest {

    @InjectMocks
    private ServiceImpl service;

    @Mock
    private PlaceRepository placeRepository;

    @Test
    void findAll() {
        // Preparar los datos de prueba
        List<PlaceModel> mockPlaces = List.of(
                PlaceModel.builder()
                        .name("Parque Central")
                        .city("Madrid")
                        .address("Calle de la Paz, 12")
                        .problem("Sin rampas")
                        .ranking(2)
                        .imageUrl("http://example.com/image1.jpg").build(),
                PlaceModel.builder()
                        .name("Biblioteca Nacional")
                        .city("Madrid")
                        .address("Calle de Ruiz de Alarcón, 23")
                        .problem("Sin rampas")
                        .ranking(1)
                        .imageUrl("http://example.com/image2.jpg").build()
        );
        when(placeRepository.findAll()).thenReturn(mockPlaces);

        List<PlaceModel> result = service.findAll();

        System.out.println(result);

        assertFalse(result.isEmpty(), "La lista de lugares debería contener datos");
    }

    @Test
    void findById() {

    }

    @Test
    void save() {

    }

    @Test
    void deleteById() {

    }

    @Test
    void findByCity() {

    }

    @Test
    void findByNameContainingIgnoreCase() {

    }
}