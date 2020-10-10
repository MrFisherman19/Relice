package com.mrfisherman.relice.Service.Office;

import com.mrfisherman.relice.Dto.BuildingDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BuildingService {

    void saveBuilding(BuildingDto buildingMinimalDTO);

    void deleteBuilding(Long id);

    BuildingDto findBuildingById(Long id);

    BuildingDto getOneById(Long id);

    BuildingDto findBuildingByName(String name);

    List<BuildingDto> findAllBuildings();

}
