package com.mrfisherman.relice.Entity.Property;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Dimensions {

    private Double width;
    private Double height;
    private Double depth;

}
