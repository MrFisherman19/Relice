package com.mrfisherman.relice.Entity.Property;

import com.mrfisherman.relice.Entity.Building.Floor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Localization {

    @OneToOne(targetEntity = Floor.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "FLOOR_ID")
    private Floor floor;

    private int xAxis;
    private int yAxis;

}
