package com.mrfisherman.relice.Entity.Building;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mrfisherman.relice.Entity.BaseEntity;
import com.mrfisherman.relice.Entity.Building.Building;
import com.mrfisherman.relice.Entity.NamedEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Floor extends NamedEntity {

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BUILDING_ID")
    private Building building;

    public Floor(Building building, String floorName) {
        this.building = building;
        super.setName(floorName);
    }
}
