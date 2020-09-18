package com.mrfisherman.relice.Entity.Asset;

import com.mrfisherman.relice.Entity.NamedEntity;
import com.mrfisherman.relice.Entity.Property.Localization;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance
@Getter
@Setter
public class AssetEntity extends NamedEntity {

    private String prefix;
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

    @Embedded
    private Localization localization;


}
