package com.mrfisherman.relice.Controller;

import com.mrfisherman.relice.Dto.BuildingDto;
import com.mrfisherman.relice.Dto.FloorDto;
import com.mrfisherman.relice.Service.Office.BuildingService;
import com.mrfisherman.relice.Service.Office.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/office")
public class OfficeController {

    private final BuildingService buildingService;
    private final FloorService floorService;

    @Autowired
    public OfficeController(BuildingService buildingService, FloorService floorService) {
        this.buildingService = buildingService;
        this.floorService = floorService;
    }

    @GetMapping("/getAllBuildings")
    public Set<BuildingDto> getAllBuildings() {
        return buildingService.findAllBuildings();
    }

    @GetMapping(value = "/getBuilding", params = "id")
    public BuildingDto getBuilding(@RequestParam Long id) {
        return buildingService.findBuildingById(id);
    }

    @PostMapping("/createBuilding")
    public ResponseEntity<?> createBuilding(@RequestBody BuildingDto building) {
        buildingService.saveBuilding(building);
        return ResponseEntity.ok("Building successfully created!");
    }

    @PutMapping("/updateBuilding")
    public ResponseEntity<?> updateBuilding(@RequestBody BuildingDto building) {
        System.out.println(building.getId());
        BuildingDto buildingToUpdate = buildingService.findBuildingById(building.getId());
        buildingToUpdate.setName(building.getName());
        buildingToUpdate.setOwner(building.getOwner());
        buildingToUpdate.setAddress(building.getAddress());
        buildingToUpdate.setImageUrl(building.getImageUrl());
        buildingService.saveBuilding(building);
        return ResponseEntity.ok("Building successfully updated!");
    }

    @GetMapping("/getAllFloors")
    public List<FloorDto> getAllFloors() {
        return floorService.findAllFloors();
    }

    @GetMapping(value = "/getFloor", params = "id")
    public FloorDto getFloor(@RequestParam Long id) {
        return floorService.findFloorById(id);
    }

    @GetMapping(value = "/getFloorsByBuilding", params = "id")
    public List<FloorDto> getFloorByBuilding(@RequestParam Long id) {
        return floorService.findAllFloorsByBuildingId(id);
    }


}
