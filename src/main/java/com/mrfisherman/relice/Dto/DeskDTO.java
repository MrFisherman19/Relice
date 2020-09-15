
package com.mrfisherman.relice.Dto;

import com.mrfisherman.relice.Entity.Furnitures.FurnitureConditionState;
import com.mrfisherman.relice.Entity.Furnitures.FurnitureLocationState;

import java.util.Set;

public class DeskDTO {

    private Long id;
    private String deskNumber;
    private String prefix;
    private String additionalNote;
    private LocalizationDTO localization;
    private String furnitureConditionState;
    private String furnitureLocationState;
    private Set<ElectronicEquipmentDTO> electronicEquipments;

    public DeskDTO() {

    }

    public DeskDTO(String deskNumber, String additionalNote, LocalizationDTO localization, String furnitureConditionState, String furnitureLocationState, Set<ElectronicEquipmentDTO> electronicEquipments) {
        this.deskNumber = deskNumber;
        this.additionalNote = additionalNote;
        this.localization = localization;
        this.furnitureConditionState = furnitureConditionState;
        this.furnitureLocationState = furnitureLocationState;
        this.electronicEquipments = electronicEquipments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeskNumber() {
        return deskNumber;
    }

    public void setDeskNumber(String deskNumber) {
        this.deskNumber = deskNumber;
    }

    public String getAdditionalNote() {
        return additionalNote;
    }

    public void setAdditionalNote(String additionalNote) {
        this.additionalNote = additionalNote;
    }

    public LocalizationDTO getLocalization() {
        return localization;
    }

    public void setLocalization(LocalizationDTO localization) {
        this.localization = localization;
    }

    public Set<ElectronicEquipmentDTO> getElectronicEquipments() {
        return electronicEquipments;
    }

    public void setElectronicEquipments(Set<ElectronicEquipmentDTO> electronicEquipments) {
        this.electronicEquipments = electronicEquipments;
    }
    public String getFurnitureConditionState() {
        return furnitureConditionState;
    }

    public void setFurnitureConditionState(FurnitureConditionState furnitureConditionState) {
        this.furnitureConditionState = furnitureConditionState.name();
    }

    public String getFurnitureLocationState() {
        return furnitureLocationState;
    }

    public void setFurnitureLocationState(FurnitureLocationState furnitureLocationState) {
        this.furnitureLocationState = furnitureLocationState.name();
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
