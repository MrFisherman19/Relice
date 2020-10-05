package com.mrfisherman.relice.Entity.Property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Color {

    @Min(0)
    @Max(255)
    private int r = 0;

    @Min(0)
    @Max(255)
    private int g = 0;

    @Min(0)
    @Max(255)
    private int b = 0;

    @Min(0)
    @Max(255)
    private int a = 0;
}
