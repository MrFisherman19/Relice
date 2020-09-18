package com.mrfisherman.relice.Entity.Building;

import com.fasterxml.jackson.annotation.*;
import com.mrfisherman.relice.Entity.BaseEntity;
import com.mrfisherman.relice.Entity.NamedEntity;
import com.mrfisherman.relice.Entity.Property.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Building extends NamedEntity {

    @Embedded
    private Address address;

    private String owner;

    @Column(length = 1000)
    private String imageUrl;

    @JsonBackReference
    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY, targetEntity = Floor.class)
    private Set<Floor> floors;

    public Building(String nameOfBuilding) {
        super.setName(nameOfBuilding);
    }
}
