
package com.mrfisherman.relice.Pojo;


public class ElectronicEquipmentDTO {

    private Long electronicId;
    private String client;
    private String externalId;
    private String type;
    private String additionalNote;
    private LocalizationDTO localization;

    public Long getElectronicId() {
        return electronicId;
    }

    public void setElectronicId(Long electronicId) {
        this.electronicId = electronicId;
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

    public void setType(String type) {
        this.type = type;
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
