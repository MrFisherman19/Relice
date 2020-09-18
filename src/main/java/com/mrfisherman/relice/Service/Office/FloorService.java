package com.mrfisherman.relice.Service.Office;

import com.mrfisherman.relice.Dto.FloorDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FloorService {

    void saveFloor(FloorDto floorDTO);

    void deleteFloor(Long id);

    FloorDto findFloorById(Long id);

    FloorDto getOneById(Long id);

    List<FloorDto> findAllFloorsByBuildingId(Long buildingId);

    List<FloorDto> findAllFloors();

}
