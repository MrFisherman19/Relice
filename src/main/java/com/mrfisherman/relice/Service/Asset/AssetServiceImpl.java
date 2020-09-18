package com.mrfisherman.relice.Service.Asset;

import com.mrfisherman.relice.Dto.AssetDto;
import com.mrfisherman.relice.Entity.Asset.AssetEntity;
import com.mrfisherman.relice.Repository.AssetBaseRepository;
import com.mrfisherman.relice.Repository.AssetRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final ModelMapper modelMapper;

    public AssetServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void saveAsset(AssetDto asset) {
        assetRepository.save(modelMapper.map(asset, AssetEntity.class));
    }

    @Override
    public void deleteAsset(Long id) {
        assetRepository.deleteById(id);
    }

    @Override
    public AssetDto findAssetById(Long id) {
        return modelMapper.map(assetRepository.findById(id).orElseThrow(EntityNotFoundException::new), AssetDto.class);
    }

    @Override
    public AssetDto getOneById(Long id) {
        return modelMapper.map(assetRepository.getOne(id), AssetDto.class);
    }

    @Override
    public List<AssetDto> findAllAssets() {
        List<AssetEntity> desks = assetRepository.findAllWithoutNPlusOne();
        return modelMapper.map(desks, new TypeToken<List<AssetEntity>>() {}.getType());
    }
}
