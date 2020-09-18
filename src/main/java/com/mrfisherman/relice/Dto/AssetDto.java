package com.mrfisherman.relice.Dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssetDto {

    private Long id;
    private String name;
    private String prefix;
    private String additionalNote;

    @Setter(AccessLevel.NONE)
    private String assetConditionState;

    @Setter(AccessLevel.NONE)
    private String assetLocationState;

    @Setter(AccessLevel.NONE)
    private String assetType;

    private LocalizationDto localization;

    public void setAssetConditionState(String assetConditionState) {
        this.assetConditionState = enumForm(assetConditionState);
    }

    public void setAssetLocationState(String assetLocationState) {
        this.assetLocationState = enumForm(assetLocationState);
    }

    public void setAssetType(String assetType) {
        this.assetType = enumForm(assetType);
    }

    private String enumForm(String property) {
        return property.toUpperCase().replaceAll(" ","_");
    }
}
