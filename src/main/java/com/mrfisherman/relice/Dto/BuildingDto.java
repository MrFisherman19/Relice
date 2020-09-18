package com.mrfisherman.relice.Dto;

import com.mrfisherman.relice.Entity.Property.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BuildingDto {

    private Long id;
    private String name;
    private Address address;
    private String owner;
    private String imageUrl;
    private int numberOfFloors;

}
