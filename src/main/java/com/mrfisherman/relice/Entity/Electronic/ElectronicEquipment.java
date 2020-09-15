package com.mrfisherman.relice.Entity.Electronic;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mrfisherman.relice.Entity.Furnitures.Desk;
import com.mrfisherman.relice.Entity.PrefixEntity;
import com.mrfisherman.relice.Entity.Property.Localization;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ElectronicEquipment extends PrefixEntity {

    private String client;
    private String externalId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ElectronicEquipmentType type;
    private String additionalNote;

    @Embedded
    private Localization localization;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DESK_ID")
    private Desk desk;

    public ElectronicEquipment() { }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externId) {
        this.externalId = externId;
    }

    public ElectronicEquipmentType getType() {
        return type;
    }

    public void setType(ElectronicEquipmentType type) {
        this.type = type;
        super.setPrefix(type.getPrefix());
    }

    public String getAdditionalNote() {
        return additionalNote;
    }

    public void setAdditionalNote(String additionalNote) {
        this.additionalNote = additionalNote;
    }

    public Localization getLocalization() {
        return localization;
    }

    public void setLocalization(Localization localization) {
        this.localization = localization;
    }

    public Desk getDesk() {
        return desk;
    }

    public void setDesk(Desk desk) {
        this.desk = desk;
    }
}
