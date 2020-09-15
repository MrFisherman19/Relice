package com.mrfisherman.relice.Controller;

import com.mrfisherman.relice.Dto.BuildingDTO;
import com.mrfisherman.relice.Dto.FloorDTO;
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
    public Set<BuildingDTO> getAllBuildings() {
        return buildingService.findAllBuildings();
    }

    @GetMapping(value = "/getBuilding", params = "id")
    public BuildingDTO getBuilding(@RequestParam Long id) {
        return buildingService.findBuildingById(id);
    }

    @PostMapping("/createBuilding")
    public ResponseEntity<?> createBuilding(@RequestBody BuildingDTO building) {
        buildingService.saveBuilding(building);
        return ResponseEntity.ok("Building successfully created!");
    }

    @PutMapping("/updateBuilding")
    public ResponseEntity<?> updateBuilding(@RequestBody BuildingDTO building) {
        System.out.println(building.getId());
        BuildingDTO buildingToUpdate = buildingService.findBuildingById(building.getId());
        buildingToUpdate.setNameOfBuilding(building.getNameOfBuilding());
        buildingToUpdate.setOwner(building.getOwner());
        buildingToUpdate.setAddress(building.getAddress());
        buildingToUpdate.setImageUrl(building.getImageUrl());
        buildingService.saveBuilding(building);
        return ResponseEntity.ok("Building successfully updated!");
    }

    @GetMapping("/getAllFloors")
    public List<FloorDTO> getAllFloors() {
        return floorService.findAllFloors();
    }

    @GetMapping(value = "/getFloor", params = "id")
    public FloorDTO getFloor(@RequestParam Long id) {
        return floorService.findFloorById(id);
    }

    @GetMapping(value = "/getFloorByBuilding", params = "id")
    public List<FloorDTO> getFloorByBuilding(@RequestParam Long id) {
        return floorService.findAllFloorsByBuildingId(id);
    }


}
