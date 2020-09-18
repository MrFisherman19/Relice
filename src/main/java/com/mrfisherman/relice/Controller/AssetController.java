package com.mrfisherman.relice.Controller;

import com.mrfisherman.relice.Dto.AssetDto;
import com.mrfisherman.relice.Service.Asset.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/asset")
public class AssetController {

    private final AssetService assetService;

    @Autowired
    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping("/getAllAssets")
    public List<AssetDto> getAllAssets() {
        return assetService.findAllAssets();
    }

    @PostMapping("/createAsset")
    public ResponseEntity<?> createAsset(@RequestBody AssetDto asset) {
        assetService.saveAsset(asset);
        return ResponseEntity.ok("Asset successfully created!");
    }

    @GetMapping(value = "/getAsset", params = "id")
    public AssetDto getAsset(@RequestParam Long id) throws Throwable {
        return assetService.findAssetById(id);
    }

    @PutMapping("/updateAsset")
    public ResponseEntity<?> updateAsset(@RequestBody AssetDto updatedAsset) throws Throwable {
        AssetDto asset = assetService.findAssetById(updatedAsset.getId());
        asset.setLocalization(updatedAsset.getLocalization());
        asset.setAdditionalNote(updatedAsset.getAdditionalNote());
        asset.setAssetConditionState(updatedAsset.getAssetConditionState());
        asset.setAssetLocationState(updatedAsset.getAssetLocationState());
        assetService.saveAsset(asset);
        return ResponseEntity.ok("Asset successfully updated!");
    }

    @DeleteMapping("/deleteAsset")
    public ResponseEntity<?> deleteAsset(Long id) {
        assetService.deleteAsset(id);
        return ResponseEntity.ok("Asset successfully deleted!");
    }
}
