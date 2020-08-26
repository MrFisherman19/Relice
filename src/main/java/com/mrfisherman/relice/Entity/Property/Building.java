package com.mrfisherman.relice.Entity.Property;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Building {

    @Id
    @GeneratedValue
    private Long buildingId;

    @Embedded
    private Address address;

    private String nameOfBuilding;
    private int numberOfFloors;

    @JsonBackReference
    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = Floor.class)
    private Set<Floor> floor;

    public Building() {}

    public Building(String nameOfBuilding) {
        this.nameOfBuilding = nameOfBuilding;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getNameOfBuilding() {
        return nameOfBuilding;
    }

    public void setNameOfBuilding(String nameOfBuilding) {
        this.nameOfBuilding = nameOfBuilding;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public Set<Floor> getFloor() {
        return floor;
    }

    public void setFloor(Set<Floor> floor) {
        this.floor = floor;
    }

}
