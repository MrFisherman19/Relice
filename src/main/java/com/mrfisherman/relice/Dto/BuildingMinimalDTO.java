
package com.mrfisherman.relice.Dto;

import com.mrfisherman.relice.Entity.Property.Address;

public class BuildingMinimalDTO {

    private Long id;
    private String buildingName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
}
