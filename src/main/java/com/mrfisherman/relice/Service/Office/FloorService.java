package com.mrfisherman.relice.Service.Office;

import com.mrfisherman.relice.Dto.FloorDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FloorService {

    Long saveFloor(FloorDto floorDto);

    void deleteFloor(Long id);

    void updateFloor(FloorDto floorDto);

    FloorDto findFloorById(Long id);

    List<FloorDto> findAllFloorsByBuildingId(Long buildingId);

    List<FloorDto> findAllFloors();

}
