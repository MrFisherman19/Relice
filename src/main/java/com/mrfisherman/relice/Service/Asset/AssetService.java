package com.mrfisherman.relice.Service.Asset;

import com.mrfisherman.relice.Dto.AssetDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AssetService {

    void saveAsset(AssetDto asset);

    void deleteAsset(Long id);

    AssetDto findAssetById(Long id) throws Throwable;

    AssetDto getOneById(Long id);

    List<AssetDto> findAllAssets();

}