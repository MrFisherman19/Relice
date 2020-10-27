package com.mrfisherman.relice.Service.Office;

import com.mrfisherman.relice.Dto.BuildingDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BuildingService {

    void saveBuilding(BuildingDto buildingDto);

    void deleteBuilding(Long id);

    void updateBuilding(BuildingDto buildingDto);

    BuildingDto findBuildingById(Long id);

    BuildingDto getOneById(Long id);

    BuildingDto findBuildingByName(String name);

    List<BuildingDto> findAllBuildings();

}
