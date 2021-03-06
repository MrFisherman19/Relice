package com.mrfisherman.relice.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class BuildingMinimalDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

}
