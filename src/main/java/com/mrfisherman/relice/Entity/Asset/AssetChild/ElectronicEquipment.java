package com.mrfisherman.relice.Entity.Asset.AssetChild;

import com.mrfisherman.relice.Entity.Asset.AssetEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class ElectronicEquipment extends AssetEntity {

    private String externalId;
    private String client;

}
