package com.mrfisherman.relice.Service.Asset;

import com.mrfisherman.relice.Dto.AssetDto;
import com.mrfisherman.relice.Entity.Asset.AssetEntity;
import com.mrfisherman.relice.Repository.AssetRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final ModelMapper modelMapper;

    public AssetServiceImpl(AssetRepository assetRepository, ModelMapper modelMapper) {
        this.assetRepository = assetRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long saveAsset(AssetDto asset) {
        return assetRepository.save(modelMapper.map(asset, AssetEntity.class)).getId();
    }

    @Override
    public List<Long> saveAssets(List<AssetDto> assets) {
        return assets
                .stream()
                .map(x -> assetRepository.save(modelMapper.map(x, AssetEntity.class)).getId())
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAsset(Long id) throws EntityNotFoundException {
        if (assetRepository.existsById(id)) {
            assetRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("No asset with id: " + id);
        }
    }

    @Override
    public AssetDto findAssetById(Long id) {
        AssetDto asset;
        if (assetRepository.existsById(id)) {
            asset = modelMapper.map(assetRepository.findById(id), AssetDto.class);
        } else {
            throw new EntityNotFoundException("No asset with id: " + id);
        }
        return asset;
    }

    @Override
    public Set<AssetDto> findAssetsByFloorId(Long id) {
        Set<AssetEntity> assets = assetRepository.findByFloorId(id);
        return modelMapper.map(assets, new TypeToken<Set<AssetEntity>>() {}.getType());
    }

    @Override
    public List<AssetDto> findAllAssets() {
        List<AssetEntity> assets = assetRepository.findAllWithoutNPlusOne();
        return modelMapper.map(assets, new TypeToken<List<AssetEntity>>() {}.getType());
    }

    public void updateAsset(AssetDto updatedAsset) {
        if (assetRepository.existsById(updatedAsset.getId())) {
            saveAsset(updatedAsset);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
