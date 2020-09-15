package com.mrfisherman.relice.Service.Office;

import com.mrfisherman.relice.Dto.FloorDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface FloorService {

    void saveFloor(FloorDTO floorDTO);

    void deleteFloor(Long id);

    FloorDTO findFloorById(Long id);

    FloorDTO getOneById(Long id);

    List<FloorDTO> findAllFloorsByBuildingId(Long buildingId);

    List<FloorDTO> findAllFloors();

}
