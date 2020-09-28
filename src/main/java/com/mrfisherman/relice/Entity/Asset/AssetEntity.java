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
public class AssetEntity extends NamedEntity {

    private String additionalNote;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AssetConditionState assetConditionState;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AssetLocationState assetLocationState;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AssetType assetType;

    @Getter(AccessLevel.NONE)
    @Transient
    private String typeShortcut;

    @Embedded
    private Localization localization;

    @Embedded
    private AssetMapDetails assetDetails;

    public String getTypeShortcut() {
        return assetType.getShortcut();
    }
}

