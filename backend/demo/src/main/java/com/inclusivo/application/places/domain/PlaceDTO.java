package com.inclusivo.application.places.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PlaceDTO {

    private Long id;

    private String name;

    private String city;

    private String address;

    private String problem;

    private Integer ranking;

    private String imageUrl;
}
