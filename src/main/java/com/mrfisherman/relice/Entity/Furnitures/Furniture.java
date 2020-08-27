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

    private String locationState = FurnitureLocationState.RIGHT_PLACE.name();
    private String conditionState = FurnitureConditionState.GOOD_CONDITION.name();

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

    public String getLocationState() {
        return locationState;
    }

    public void setLocationState(FurnitureLocationState locationState) {
        this.locationState = locationState.name();
    }

    public String getConditionState() {
        return conditionState;
    }

    public void setConditionState(FurnitureConditionState conditionState) {
        this.conditionState = conditionState.name();
    }
}
