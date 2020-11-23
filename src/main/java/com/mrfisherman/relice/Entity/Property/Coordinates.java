package com.mrfisherman.relice.Entity.Property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Min;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Coordinates {

    @Min(0)
    private int xAxis;

    @Min(0)
    private int yAxis;

    @Min(0)
    private int zAxis;

}
