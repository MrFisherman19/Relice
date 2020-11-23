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

    private String additionalNote = "";

    @NotNull
    @Setter(AccessLevel.NONE)
    private String assetConditionState;

    @NotNull
    @Setter(AccessLevel.NONE)
    private String assetLocationState;

    @NotNull
    @Setter(AccessLevel.NONE)
    private String assetType;

    private String barcode;

    private LocalizationDto localization;

    private AssetMapDetailsDto assetMapDetails;

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
