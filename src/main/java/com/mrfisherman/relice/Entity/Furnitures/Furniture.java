package com.mrfisherman.relice.Entity.Furnitures;

import com.mrfisherman.relice.Entity.Property.Localization;

import javax.persistence.*;

@MappedSuperclass
public class Furniture {

    @Id
    @GeneratedValue
    private Long furnitureId;

    @Transient
    private String prefix;
    private String additionalNote;

    @Embedded
    private Localization localization;

    public Long getFurnitureId() {
        return furnitureId;
    }

    private void setFurnitureId(Long furnitureId) {
        this.furnitureId = furnitureId;
    }

    public String getPrefix() {
        return prefix;
    }

    protected void setPrefix(String prefix) {
        this.prefix = prefix;
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
}
