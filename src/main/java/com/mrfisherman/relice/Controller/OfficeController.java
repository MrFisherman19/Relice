package com.mrfisherman.relice.Controller;

import com.mrfisherman.relice.Controller.ExceptionHandler.HandlerUtil;
import com.mrfisherman.relice.Dto.BuildingDto;
import com.mrfisherman.relice.Dto.FloorDto;
import com.mrfisherman.relice.Service.Office.BuildingService;
import com.mrfisherman.relice.Service.Office.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;

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
    public List<BuildingDto> getAllBuildings() {
        return buildingService.findAllBuildings();
    }

    @GetMapping(value = "/getBuilding", params = "id")
    public BuildingDto getBuilding(@RequestParam Long id) {
        return buildingService.findBuildingById(id);
    }

    @PostMapping("/createBuilding")
    public ResponseEntity<?> createBuilding(@RequestBody BuildingDto building) {

        Long newBuildingId = buildingService.saveBuilding(building);

        return ResponseEntity.ok().body(newBuildingId);
    }

    @DeleteMapping(value = "/deleteBuilding", params = "id")
    public ResponseEntity<?> deleteBuilding(Long id) {
        buildingService.deleteBuilding(id);
        return ResponseEntity.ok("Building successfully deleted!");
    }

    @DeleteMapping(value = "/deleteFloor", params = "id")
    public ResponseEntity<?> deleteFloor(Long id) {
        floorService.deleteFloor(id);
        return ResponseEntity.ok("Floor successfully deleted!");
    }

    @PostMapping("/createFloor")
    public ResponseEntity<?> createFloor(@RequestBody FloorDto floor) {

        Long newFloorId = floorService.saveFloor(floor);

        return ResponseEntity.ok().body(newFloorId);
    }

    @PutMapping("/updateBuilding")
    public ResponseEntity<?> updateBuilding(@RequestBody BuildingDto building) {

        buildingService.updateBuilding(building);

        return ResponseEntity.ok("Building successfully updated!");
    }

    @PutMapping("/updateFloor")
    public ResponseEntity<?> updateFloor(@RequestBody FloorDto floor) {

        floorService.updateFloor(floor);

        return ResponseEntity.ok("Floor successfully updated!");
    }

    @GetMapping("/getAllFloors")
    public List<FloorDto> getAllFloors() { return floorService.findAllFloors(); }

    @GetMapping(value = "/getFloor", params = "id")
    public FloorDto getFloor(@RequestParam Long id) { return floorService.findFloorById(id); }

    @GetMapping(value = "/getFloorsByBuilding", params = "id")
    public List<FloorDto> getFloorByBuilding(@RequestParam Long id) { return floorService.findAllFloorsByBuildingId(id); }

    @ExceptionHandler({org.hibernate.exception.ConstraintViolationException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public HashMap<String, String> handleNullValueIdParsing(Exception e) {
        return HandlerUtil.createResponseWithMessageAndError("To remove an item, remove all of its children and references to this item first.", e);
    }
}
