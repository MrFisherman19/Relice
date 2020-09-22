
package com.mrfisherman.relice.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LocalizationDto {

    private FloorDto floor;
    private int xAxis;
    private int yAxis;

}
