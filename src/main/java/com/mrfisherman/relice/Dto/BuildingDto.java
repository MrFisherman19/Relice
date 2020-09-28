package com.mrfisherman.relice.Dto;

import com.mrfisherman.relice.Entity.Property.Address;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BuildingDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    private Address address;
    private String owner;
    private String imageUrl;

    @Min(1)
    @Max(250)
    private int numberOfFloors;

}
