package com.mrfisherman.relice.Service.Asset;

import com.mrfisherman.relice.Repository.Projection.AssetConditionStateByAssetType;
import com.mrfisherman.relice.Repository.Projection.AssetConditionStateCount;
import java.util.List;

public interface AssetStatsService {

    List<AssetConditionStateCount> getAssetConditionStateCount();

    List<AssetConditionStateByAssetType> getAssetConditionStateCountByAssetType();

}
