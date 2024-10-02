package com.inclusivo.application.places.application.converters;

import com.inclusivo.application.places.domain.PlaceDTO;
import com.inclusivo.application.places.domain.PlaceModel;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ConvertToPlaceModel implements Converter<PlaceDTO, PlaceModel> {

    @Override
    public PlaceModel convert(@NonNull PlaceDTO placeDTO) {
        Assert.notNull(placeDTO, "Place model value is null");
        return PlaceModel.builder()
                         .id(placeDTO.getId())
                         .name(placeDTO.getName())
                         .city(placeDTO.getCity())
                         .address(placeDTO.getAddress())
                         .problem(placeDTO.getProblem())
                         .ranking(placeDTO.getRanking())
                         .imageUrl(placeDTO.getImageUrl())
                         .build();
    }
}
