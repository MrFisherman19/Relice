
package com.mrfisherman.relice.Dto;

import com.mrfisherman.relice.Entity.Property.Coordinates;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class LocalizationDto {

    @NotNull
    private FloorDto floor;

    private FloorDto floor_planned;

    private FloorDto floor_previous;

    private Coordinates coordinates;

    private Coordinates coordinates_planned;

    private Coordinates coordinates_previous;

}
