package com.mrfisherman.relice.Entity.Furnitures;

import com.mrfisherman.relice.Entity.PrefixEntity;
import com.mrfisherman.relice.Entity.Property.Localization;
import com.sun.istack.NotNull;

import javax.persistence.*;

@MappedSuperclass
public class Furniture extends PrefixEntity {

    private String additionalNote;

    @Embedded
    private Localization localization;

    @NotNull
    @Enumerated(EnumType.STRING)
    private FurnitureLocationState locationState = FurnitureLocationState.RIGHT_PLACE;

    @NotNull
    @Enumerated(EnumType.STRING)
    private FurnitureConditionState conditionState = FurnitureConditionState.GOOD_CONDITION;

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

    public FurnitureLocationState getLocationState() {
        return locationState;
    }

    public void setLocationState(FurnitureLocationState locationState) {
        this.locationState = locationState;
    }

    public FurnitureConditionState getConditionState() {
        return conditionState;
    }

    public void setConditionState(FurnitureConditionState conditionState) {
        this.conditionState = conditionState;
    }
}
