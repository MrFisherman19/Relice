package com.mrfisherman.relice.Controller;

import com.mrfisherman.relice.Controller.ExceptionHandler.HandlerUtil;
import com.mrfisherman.relice.Dto.AssetDto;
import com.mrfisherman.relice.Service.Asset.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

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

    @PutMapping("/updateAssets")
    public ResponseEntity<?> updateAssets(@Valid @RequestBody List<AssetDto> updatedAsset) {
        updatedAsset.forEach(assetService::updateAsset);
        return ResponseEntity.ok("Asset successfully updated!");
    }

    @DeleteMapping(value = "/deleteAsset", params = "id")
    public ResponseEntity<?> deleteAsset(Long id) {
        assetService.deleteAsset(id);
        return ResponseEntity.ok("Asset successfully deleted!");
    }

    @ExceptionHandler({IllegalArgumentException.class, ConstraintViolationException.class, EntityNotFoundException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = ID_MUST_NOT_BE_NULL_MESSAGE)
    public HashMap<String, String> handleNullValueIdParsing(Exception e) {
        return HandlerUtil.createResponseWithMessageAndError(ID_MUST_NOT_BE_NULL_MESSAGE, e);
    }
}
