package com.mrfisherman.relice.Entity.Asset.AssetChild;

import com.mrfisherman.relice.Entity.Asset.AssetEntity;
import com.mrfisherman.relice.Entity.Property.Dimensions;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Furniture extends AssetEntity {

    @Embedded
    private Dimensions dimensions;

}
