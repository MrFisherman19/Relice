package com.mrfisherman.relice.Dto;

import com.mrfisherman.relice.Entity.Property.Color;
import com.mrfisherman.relice.Entity.Property.Dimensions;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class AssetMapDetailsDto {

    @NotNull
    private Dimensions dimensions;

    @NotNull
    private Color color;
}
