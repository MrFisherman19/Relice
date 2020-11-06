
package com.mrfisherman.relice.Dto;

import com.mrfisherman.relice.Entity.Property.Coordinates;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class LocalizationDto {

    @NotNull
    private FloorDto floor;

    private FloorDto floor_planned;

    private Coordinates coordinates;

    private Coordinates coordinates_planned;

}
