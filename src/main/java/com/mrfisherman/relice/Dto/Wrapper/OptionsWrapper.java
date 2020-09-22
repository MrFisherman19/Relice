package com.mrfisherman.relice.Dto.Wrapper;

import com.mrfisherman.relice.Entity.Asset.AssetConditionState;
import com.mrfisherman.relice.Entity.Asset.AssetLocationState;
import com.mrfisherman.relice.Entity.Asset.AssetType;
import com.mrfisherman.relice.Entity.User.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OptionsWrapper {

    AssetType[] assetTypes;
    AssetConditionState[] assetConditionStates;
    AssetLocationState[] assetLocationStates;
    UserRole[] userRoles;

}
