package com.mrfisherman.relice.Controller;

import com.mrfisherman.relice.Controller.ExceptionHandler.HandlerUtil;
import com.mrfisherman.relice.Dto.AssetDto;
import com.mrfisherman.relice.Dto.Wrapper.ListOfIdsWrapper;
import com.mrfisherman.relice.Entity.Asset.AssetLocationState;
import com.mrfisherman.relice.Service.Asset.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@RestController
@RequestMapping("/asset")
public class AssetController {

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
    public ResponseEntity<?> createAsset(@Valid @RequestBody AssetDto asset) {
        Long newItemId = assetService.saveAsset(asset);
        return ResponseEntity.ok().body(newItemId);
    }

    @PostMapping(value = "/createMultiplyAssets", params = "amount")
    public ResponseEntity<?> createMultiplyAssets(@Valid @RequestBody AssetDto asset, @RequestParam int amount) {
        return ResponseEntity.ok().body(
                LongStream.range(0, amount).map(x -> assetService.saveAsset(asset)).boxed().collect(Collectors.toList()));
    }

    @PostMapping("/createAssets")
    public ResponseEntity<?> createAssets(@Valid @RequestBody List<AssetDto> assets) {
        List<Long> newItemsId = assetService.saveAssets(assets);
        return ResponseEntity.ok().body(newItemsId);
    }

    @PutMapping("/updateAsset")
    public ResponseEntity<?> updateAsset(@Valid @RequestBody AssetDto updatedAsset) {
        assetService.updateAsset(updatedAsset);
        return ResponseEntity.ok("Asset successfully updated!");
    }

    @PutMapping("/addConnection")
    public ResponseEntity<?> addConnection(@Valid @RequestBody AssetDto updatedAsset) {
        updatedAsset.setAssetLocationState(AssetLocationState.TO_RELOCATION.name());
        assetService.updateAsset(updatedAsset);
        return ResponseEntity.ok("Connection successfully added!");
    }

    @PutMapping("/updateAssets")
    public ResponseEntity<?> updateAssets(@Valid @RequestBody List<AssetDto> updatedAsset) {
        updatedAsset.forEach(assetService::updateAsset);
        return ResponseEntity.ok("Assets successfully updated!");
    }

    @PatchMapping(value = "/relocateAssets", consumes = "application/json")
    public ResponseEntity<?> relocateAssets(@RequestBody ListOfIdsWrapper listOfIds) {
        for (Long id : listOfIds.getListOfIds()) {
            AssetDto asset = assetService.findAssetById(id);
            asset.setAssetLocationState(AssetLocationState.RIGHT_PLACE.name());
            assetService.updateAsset(asset);
        }
        return ResponseEntity.ok("Assets successfully relocated!");
    }

    @DeleteMapping(value = "/deleteAsset", params = "id")
    public ResponseEntity<?> deleteAsset(Long id) {
        assetService.deleteAsset(id);
        return ResponseEntity.ok("Asset successfully deleted!");
    }

    @PostMapping(value = "/deleteAssets", consumes = "application/json")
    public ResponseEntity<?> deleteAssets(@RequestBody ListOfIdsWrapper listOfIds) {
        listOfIds.getListOfIds().forEach(assetService::deleteAsset);
        return ResponseEntity.ok("Assets successfully deleted!");
    }

    @ExceptionHandler({IllegalArgumentException.class, ConstraintViolationException.class, EntityNotFoundException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public HashMap<String, String> handleNullValueIdParsing(Exception e) {
        return HandlerUtil.createResponseWithMessageAndError("Id of asset must not be null!", e);
    }
}
