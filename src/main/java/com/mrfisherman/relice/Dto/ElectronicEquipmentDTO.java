
package com.mrfisherman.relice.Dto;


import com.mrfisherman.relice.Entity.Electronic.ElectronicEquipmentType;

public class ElectronicEquipmentDTO {

    private Long id;
    private String client;
    private String externalId;
    private String type;
    private String additionalNote;
    private LocalizationDTO localization;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getType() {
        return type;
    }

    public void setType(ElectronicEquipmentType type) {
        this.type = type.name();
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
}
