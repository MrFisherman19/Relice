package com.mrfisherman.relice.Controller;

import com.mrfisherman.relice.Controller.ExceptionHandler.HandlerUtil;
import com.mrfisherman.relice.Dto.AssetDto;
import com.mrfisherman.relice.Service.Asset.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/asset")
public class AssetController {

    private final static String ID_MUST_NOT_BE_NULL_MESSAGE = "Id must not be null!";

    private final AssetService assetService;

    @Autowired
    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping(value = "/getAsset", params = "id")
    public AssetDto getAsset(@RequestParam Long id) {
        return assetService.findAssetById(id);
    }

    @GetMapping("/getAllAssets")
    public List<AssetDto> getAllAssets() {
        return assetService.findAllAssets();
    }

    @GetMapping(value = "/getAssetsByFloorId", params = "id")
    public Set<AssetDto> getAssetsByFloorId(@RequestParam Long id) {
        return assetService.findAssetsByFloorId(id);
    }

    @PostMapping("/createAsset")
    public ResponseEntity<?> createAsset(@RequestBody AssetDto asset) {
        Long newItemId = assetService.saveAsset(asset);
        return ResponseEntity.ok().body(newItemId);
    }

    @PutMapping("/updateAsset")
    public ResponseEntity<?> updateAsset(@RequestBody AssetDto updatedAsset) {
        Long id = updatedAsset.getId();
        if (id != null) {
            AssetDto asset = assetService.findAssetById(id);
            asset.setLocalization(updatedAsset.getLocalization());
            asset.setAdditionalNote(updatedAsset.getAdditionalNote());
            asset.setAssetConditionState(updatedAsset.getAssetConditionState());
            asset.setAssetLocationState(updatedAsset.getAssetLocationState());
            asset.setAssetType(updatedAsset.getAssetType());
            asset.setName(updatedAsset.getName());
            assetService.saveAsset(asset);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ID_MUST_NOT_BE_NULL_MESSAGE);
        }
        return ResponseEntity.ok("Asset successfully updated!");
    }

    @DeleteMapping(value = "/deleteAsset", params = "id")
    public ResponseEntity<?> deleteAsset(Long id) {
        assetService.deleteAsset(id);
        return ResponseEntity.ok("Asset successfully deleted!");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = ID_MUST_NOT_BE_NULL_MESSAGE)
    public HashMap<String, String> handleNullValueIdParsing(Exception e) {
        return HandlerUtil.createResponseWithMessageAndError(ID_MUST_NOT_BE_NULL_MESSAGE, e);
    }
}
