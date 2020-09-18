package com.mrfisherman.relice.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FloorDto {

    private Long id;
    private String name;
    private BuildingMinimalDto building;

}
