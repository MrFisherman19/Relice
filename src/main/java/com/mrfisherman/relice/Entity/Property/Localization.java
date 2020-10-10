package com.mrfisherman.relice.Entity.Property;

import com.mrfisherman.relice.Entity.Building.Floor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Localization {

    @OneToOne(targetEntity = Floor.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "FLOOR_ID")
    private Floor floor;

    @Min(0)
    @Max(1000)
    private int xAxis;

    @Min(0)
    @Max(2400)
    private int yAxis;

    @Min(0)
    @Max(999)
    private int zAxis;

}
