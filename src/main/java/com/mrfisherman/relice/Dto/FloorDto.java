package com.mrfisherman.relice.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class FloorDto {

    @NotNull
    private Long id;

    private String name;
    private String description;
    @NotNull
    private BuildingMinimalDto building;

}
