package com.mrfisherman.relice.Dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AssetDto {

    private Long id;
    private String name;
    private String additionalNote;

    @Setter(AccessLevel.NONE)
    private String assetConditionState;

    @Setter(AccessLevel.NONE)
    private String assetLocationState;

    @Setter(AccessLevel.NONE)
    private String assetType;

    private String typeShortcut;

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
