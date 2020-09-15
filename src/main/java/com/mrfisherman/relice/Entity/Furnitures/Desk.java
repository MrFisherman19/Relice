package com.mrfisherman.relice.Entity.Furnitures;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mrfisherman.relice.Entity.Electronic.ElectronicEquipment;

import javax.persistence.*;
import java.util.Set;

@Entity
public final class Desk extends Furniture {

    private final String prefix = "DSC";

    //it have to be string because desk number is in format floorNumber-deskNumber like 4-124
    private String deskNumber;

    @JsonManagedReference
    @OneToMany(mappedBy = "desk", fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = ElectronicEquipment.class)
    private Set<ElectronicEquipment> electronicEquipments;

    public Desk() {
        super.setPrefix(prefix);
    }

    public String getDeskNumber() {
        return deskNumber;
    }

    public void setDeskNumber(String deskNumber) {
        this.deskNumber = deskNumber;
    }

    public Set<ElectronicEquipment> getElectronicEquipments() {
        return electronicEquipments;
    }

    public void setElectronicEquipments(Set<ElectronicEquipment> electronicEquipments) {
        this.electronicEquipments = electronicEquipments;
    }
}
