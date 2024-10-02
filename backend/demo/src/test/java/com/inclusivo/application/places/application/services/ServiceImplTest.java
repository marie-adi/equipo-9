package com.inclusivo.application.places.application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertFalse;
import com.inclusivo.application.places.domain.PlaceModel;
import com.inclusivo.application.places.exceptions.PlaceDeleteException;
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
    private PlaceServiceImpl placeService;

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

        List<PlaceModel> result = placeService.findAll();

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

        Optional<PlaceModel> result = placeService.findById(1L);

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

        PlaceModel savedPlace = placeService.save(mockPlace);

        assertEquals(mockPlace, savedPlace, "El lugar guardado debería ser el mismo que el mock");
    }

    @Test
    void deleteById_Success() {
        // Arrange
        PlaceModel place = PlaceModel.builder()
                .id(1L)
                .name("Parque Central")
                .city("Madrid")
                .address("Calle de la Paz, 12")
                .problem("Sin rampas")
                .ranking(2)
                .imageUrl("http://example.com/image1.jpg")
                .build();

        when(placeRepository.findById(1L)).thenReturn(Optional.of(place));

        placeService.deleteById(1L);

        verify(placeRepository, times(1)).delete(place);
    }

    @Test
    void deleteById_PlaceNotFound() {

        when(placeRepository.findById(1L)).thenReturn(Optional.empty());

        PlaceDeleteException exception = assertThrows(PlaceDeleteException.class, () -> {
            placeService.deleteById(1L);
        });
        assertEquals("Error al eliminar el lugar con id 1", exception.getMessage());
    }

    @Test
    void deleteById_NullId() {
        assertThrows(IllegalArgumentException.class, () -> {
            placeService.deleteById(null);
        });
    }

    @Test
    void findByCity_Success() {

        String city = "Madrid";
        PlaceModel place = PlaceModel.builder()
                .id(1L)
                .name("Parque Central")
                .city(city)
                .address("Calle de la Paz, 12")
                .problem("Sin rampas")
                .ranking(2)
                .imageUrl("http://example.com/image1.jpg")
                .build();
        List<PlaceModel> expectedPlaces = List.of(place);

        when(placeRepository.findByCity(city)).thenReturn(expectedPlaces);

        List<PlaceModel> actualPlaces = placeService.findByCity(city);

        assertEquals(expectedPlaces, actualPlaces);
        verify(placeRepository, times(1)).findByCity(city);
    }

    @Test
    void findByCity_NullCity() {
        assertThrows(IllegalArgumentException.class, () -> {
            placeService.findByCity(null);
        });
    }

    @Test
    void findByNameContainingIgnoreCase_Success() {
        // Arrange
        String name = "parque";
        PlaceModel place = PlaceModel.builder()
                .id(1L)
                .name("Parque Central")
                .city("Madrid")
                .address("Calle de la Paz, 12")
                .problem("Sin rampas")
                .ranking(2)
                .imageUrl("http://example.com/image1.jpg")
                .build();

        when(placeRepository.findByNameContainingIgnoreCase(name)).thenReturn(Optional.of(place));

        Optional<PlaceModel> actualPlace = placeService.findByNameContainingIgnoreCase(name);

        assertTrue(actualPlace.isPresent());
        assertEquals(place, actualPlace.get());
        verify(placeRepository, times(1)).findByNameContainingIgnoreCase(name);
    }

    @Test
    void findByNameContainingIgnoreCase_NotFound() {

        String name = "noexiste";
        when(placeRepository.findByNameContainingIgnoreCase(name)).thenReturn(Optional.empty());

        Optional<PlaceModel> actualPlace = placeService.findByNameContainingIgnoreCase(name);

        assertFalse(actualPlace.isPresent());
        verify(placeRepository, times(1)).findByNameContainingIgnoreCase(name);
    }

    @Test
    void findByNameContainingIgnoreCase_NullName() {
        assertThrows(IllegalArgumentException.class, () -> {
            placeService.findByNameContainingIgnoreCase(null);
        });
    }
}