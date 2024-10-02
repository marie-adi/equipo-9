package com.inclusivo.application.places.application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
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
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ServiceImplTest {

    @InjectMocks
    private ServiceImpl service;

    @Mock
    private PlaceRepository placeRepository;

    @Test
    void findAll() {

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

        PlaceModel mockPlace = PlaceModel.builder()
                .id(1L)
                .name("Parque Central")
                .city("Madrid")
                .address("Calle de la Paz, 12")
                .problem("Sin rampas")
                .ranking(2)
                .imageUrl("http://example.com/image1.jpg")
                .build();

        when(placeRepository.findById(1L)).thenReturn(Optional.of(mockPlace));

        Optional<PlaceModel> result = service.findById(1L);

        assertEquals(mockPlace, result.orElse(null), "El lugar devuelto debería ser el mismo que el mock");
    }

    @Test
    void save() {

        PlaceModel mockPlace = PlaceModel.builder()
                .id(1L)
                .name("Parque Central")
                .city("Madrid")
                .address("Calle de la Paz, 12")
                .problem("Sin rampas")
                .ranking(2)
                .imageUrl("http://example.com/image1.jpg")
                .build();

        when(placeRepository.save(mockPlace)).thenReturn(mockPlace);

        PlaceModel savedPlace = service.save(mockPlace);

        assertEquals(mockPlace, savedPlace, "El lugar guardado debería ser el mismo que el mock");
    }

    @Test
    void deleteById() {
        Long placeId = 1L;

        service.deleteById(placeId);

        verify(placeRepository).deleteById(placeId);
    }
}