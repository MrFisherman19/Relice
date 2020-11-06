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

    @ManyToOne(targetEntity = Floor.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "FLOOR_ID")
    private Floor floor;

    @Embedded
    private Coordinates coordinates;

    @AttributeOverrides({
            @AttributeOverride(name = "xAxis", column = @Column(name = "xAxis_planned")),
            @AttributeOverride(name = "yAxis", column = @Column(name = "yAxis_planned")),
            @AttributeOverride(name = "zAxis", column = @Column(name = "zAxis_planned"))
    })
    @Embedded
    private Coordinates coordinates_planned;

}
