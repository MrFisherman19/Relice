package com.mrfisherman.relice.Service.Asset;

import com.mrfisherman.relice.Repository.AssetRepository;
import com.mrfisherman.relice.Repository.Projection.AssetConditionStateByAssetType;
import com.mrfisherman.relice.Repository.Projection.AssetConditionStateCount;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetStatServiceImpl implements AssetStatsService {

    private final AssetRepository assetRepository;

    public AssetStatServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public List<AssetConditionStateCount> getAssetConditionStateCount() {
        return assetRepository.countAssetConditionStates();
    }

    public List<AssetConditionStateByAssetType> getAssetConditionStateCountByAssetType() {
        return assetRepository.countAssetConditionStatesByType();
    }

}
