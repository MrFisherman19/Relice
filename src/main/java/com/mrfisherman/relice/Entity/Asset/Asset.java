package com.mrfisherman.relice.Entity.Asset;

import com.mrfisherman.relice.Entity.NamedEntity;
import com.mrfisherman.relice.Entity.Property.Localization;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance
@Getter
@Setter
public class Asset extends NamedEntity {

    private String additionalNote = "";

    @NotNull
    @Enumerated(EnumType.STRING)
    private AssetConditionState assetConditionState;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AssetLocationState assetLocationState;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AssetType assetType;

    @Transient
    @Getter(AccessLevel.NONE)
    private String barcode;

    @Embedded
    private Localization localization;

    @Embedded
    private AssetMapDetails assetMapDetails;

    public String getBarcode() {
        return String.format("%s-%d", getAssetType().name().substring(0,3), getId());
    }
}

