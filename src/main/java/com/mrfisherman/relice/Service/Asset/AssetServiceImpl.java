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

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final ModelMapper modelMapper;

    public AssetServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public Long saveAsset(AssetDto asset) {
        return assetRepository.save(modelMapper.map(asset, AssetEntity.class)).getId();
    }

    @Override
    public void deleteAsset(Long id) throws IllegalArgumentException {
        assetRepository.deleteById(id);
    }

    @Override
    public AssetDto findAssetById(Long id) {
        return modelMapper.map(assetRepository.findById(id).orElseThrow(EntityNotFoundException::new), AssetDto.class);
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
}
