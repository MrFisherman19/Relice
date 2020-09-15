
package com.mrfisherman.relice.Dto;

public class FloorDTO {

    private Long id;
    private int floorNumber;
    private BuildingMinimalDTO building;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public BuildingMinimalDTO getBuilding() {
        return building;
    }

    public void setBuilding(BuildingMinimalDTO building) {
        this.building = building;
    }
}
