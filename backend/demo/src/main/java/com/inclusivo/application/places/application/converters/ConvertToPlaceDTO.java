package com.inclusivo.application.places.application.converters;

import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import com.inclusivo.application.places.domain.PlaceDTO;
import com.inclusivo.application.places.domain.PlaceModel;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ConvertToPlaceDTO implements Converter<PlaceModel, PlaceDTO> {

    @Override
    public PlaceDTO convert(@NonNull PlaceModel placeModel) {
        Assert.notNull(placeModel, "Place model value is null");
        return PlaceDTO.builder()
                       .id(placeModel.getId())
                       .name(placeModel.getName())
                       .city(placeModel.getCity())
                       .address(placeModel.getAddress())
                       .problem(placeModel.getProblem())
                       .ranking(placeModel.getRanking())
                       .imageUrl(placeModel.getImageUrl())
                       .build();
    }
}
