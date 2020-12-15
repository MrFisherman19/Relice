package com.mrfisherman.relice.Service.Asset;

import com.mrfisherman.relice.Dto.AssetDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface AssetService {

    Long saveAsset(AssetDto asset);

    void deleteAsset(Long id);

    void updateAsset(AssetDto asset);

    AssetDto findAssetById(Long id);

    List<AssetDto> findAllAssets();

    List<AssetDto> findAssetsToRelocation();

    Set<AssetDto> findAssetsByFloorId(Long id);

    List<Long> saveAssets(List<AssetDto> assets);

}