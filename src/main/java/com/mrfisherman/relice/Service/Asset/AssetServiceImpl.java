package com.mrfisherman.relice.Service.Asset;

import com.mrfisherman.relice.Dto.AssetDto;
import com.mrfisherman.relice.Entity.Asset.Asset;
import com.mrfisherman.relice.Entity.Asset.AssetLocationState;
import com.mrfisherman.relice.Repository.AssetRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
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
        return assetRepository.save(modelMapper.map(asset, Asset.class)).getId();
    }

    @Override
    public List<Long> saveAssets(List<AssetDto> assets) {
        return assets
                .stream()
                .map(x -> assetRepository.save(modelMapper.map(x, Asset.class)).getId())
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
        return modelMapper.map(assetRepository.findById(id).orElseThrow(
                    () -> new EntityNotFoundException("No asset with id: " + id)), AssetDto.class);
    }

    @Override
    public Set<AssetDto> findAssetsByFloorId(Long id) {
        Set<Asset> assets = assetRepository.findByFloorId(id);
        return modelMapper.map(assets, new TypeToken<Set<AssetDto>>() {}.getType());
    }

    @Override
    public List<AssetDto> findAllAssets() {
        List<Asset> assets = assetRepository.findAllWithoutNPlusOne();
        return modelMapper.map(assets, new TypeToken<List<AssetDto>>() {}.getType());
    }

    public void updateAsset(AssetDto updatedAsset) {

            AssetDto currentAsset = findAssetById(updatedAsset.getId());
            Optional<AssetDto> currentAssetOptional = Optional.ofNullable(currentAsset);
            currentAssetOptional.ifPresentOrElse(asset -> relocateAsset(asset, updatedAsset),
                    () -> {throw new EntityNotFoundException();});

            saveAsset(updatedAsset);
    }

    private void relocateAsset(AssetDto currentAsset, AssetDto updatedAsset) {
        if (currentAsset.getAssetLocationState().equals(AssetLocationState.TO_RELOCATION.name()) &&
                !updatedAsset.getAssetLocationState().equals(AssetLocationState.TO_RELOCATION.name())) {

            if (updatedAsset.getLocalization().getFloor_planned() != null &&
                    updatedAsset.getLocalization().getCoordinates_planned() != null) {
                
                updatedAsset.getLocalization().setFloor_previous(currentAsset.getLocalization().getFloor());
                updatedAsset.getLocalization().setFloor(updatedAsset.getLocalization().getFloor_planned());
                updatedAsset.getLocalization().setCoordinates(updatedAsset.getLocalization().getCoordinates_planned());
                updatedAsset.getLocalization().setFloor_planned(null);
                updatedAsset.getLocalization().setCoordinates_planned(null);

            }
        }
    }
}
