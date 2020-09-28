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
    private int r;

    @Min(0)
    @Max(255)
    private int g;

    @Min(0)
    @Max(255)
    private int b;

    @Min(0)
    @Max(255)
    private int a;
}
