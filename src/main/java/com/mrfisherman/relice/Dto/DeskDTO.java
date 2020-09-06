
package com.mrfisherman.relice.Dto;

import java.util.Set;

public class DeskDTO {

    private Long deskId;
    private String deskNumber;
    private String additionalNote;
    private LocalizationDTO localization;
    private Set<ElectronicEquipmentDTO> electronicEquipments;

    public DeskDTO() {

    }

    public DeskDTO(String deskNumber, String additionalNote, LocalizationDTO localizationDTO, Set<ElectronicEquipmentDTO> electronicEquipmentDTOS) {
        this.deskNumber = deskNumber;
        this.additionalNote = additionalNote;
        this.localization = localizationDTO;
        this.electronicEquipments = Set.copyOf(electronicEquipmentDTOS);
    }

    public Long getDeskId() {
        return deskId;
    }

    public void setDeskId(Long deskId) {
        this.deskId = deskId;
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
}
