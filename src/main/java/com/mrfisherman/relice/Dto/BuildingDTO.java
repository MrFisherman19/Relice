package com.mrfisherman.relice.Dto;

import com.mrfisherman.relice.Entity.Property.Address;

public class BuildingDTO {

    private Long id;
    private String nameOfBuilding;
    private Address address;
    private String owner;
    private String imageUrl;
    private int numberOfFloors;

    public BuildingDTO() {
    }

    public BuildingDTO(Long id, String nameOfBuilding, String owner, Address address, int numberOfFloors, String imageUrl) {
        this.id = id;
        this.nameOfBuilding = nameOfBuilding;
        this.owner = owner;
        this.address = address;
        this.numberOfFloors = numberOfFloors;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfBuilding() {
        return nameOfBuilding;
    }

    public void setNameOfBuilding(String nameOfBuilding) {
        this.nameOfBuilding = nameOfBuilding;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
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
