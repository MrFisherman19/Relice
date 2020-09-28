package com.mrfisherman.relice.Service.Asset;

import com.mrfisherman.relice.Dto.AssetDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface AssetService {

    Long saveAsset(AssetDto asset);

    void deleteAsset(Long id);

    AssetDto findAssetById(Long id);

    List<AssetDto> findAllAssets();

    Set<AssetDto> findAssetsByFloorId(Long id);
}