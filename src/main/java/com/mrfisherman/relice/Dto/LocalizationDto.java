
package com.mrfisherman.relice.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class LocalizationDto {

    @NotNull
    private FloorWithoutBuildingDto floor;

    @Min(0)
    @Max(999)
    private int xAxis;

    @Min(0)
    @Max(1199)
    private int yAxis;

    @Min(0)
    @Max(999)
    private int zAxis;

}
