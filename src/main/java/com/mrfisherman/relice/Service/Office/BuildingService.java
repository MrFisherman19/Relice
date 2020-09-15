package com.mrfisherman.relice.Service.Office;

import com.mrfisherman.relice.Dto.BuildingDTO;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface BuildingService {

    void saveBuilding(BuildingDTO buildingMinimalDTO);

    void deleteBuilding(Long id);

    BuildingDTO findBuildingById(Long id);

    BuildingDTO getOneById(Long id);

    BuildingDTO findBuildingByNameOfBuilding(String buildingName);

    Set<BuildingDTO> findAllBuildings();

}
