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
public class Dimensions {

    @Min(1)
    private int width = 100;

    @Min(1)
    private int height = 100;

    @Min(1)
    private int depth = 1;

}
