package com.mrfisherman.relice.Entity.Property;

import com.fasterxml.jackson.annotation.*;
import com.mrfisherman.relice.Entity.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Building extends BaseEntity {

    @Embedded
    private Address address;
    private String owner;
    private String nameOfBuilding;

    @Column(length = 1000)
    private String imageUrl;

    @JsonBackReference
    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY, targetEntity = Floor.class)
    private Set<Floor> floors;

    public Building() {}

    public Building(String nameOfBuilding) {
        this.nameOfBuilding = nameOfBuilding;
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
        return getFloors().size();
    }

    public Set<Floor> getFloors() {
        return floors;
    }

    public void setFloors(Set<Floor> floor) {
        this.floors = floor;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
