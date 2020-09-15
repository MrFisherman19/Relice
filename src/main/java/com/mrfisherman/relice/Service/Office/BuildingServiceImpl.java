package com.mrfisherman.relice.Service.Office;

import com.mrfisherman.relice.Dto.BuildingDTO;
import com.mrfisherman.relice.Entity.Property.Building;
import com.mrfisherman.relice.Repository.BuildingRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Set;

@Service
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository buildingRepository;
    private final ModelMapper modelMapper;

    public BuildingServiceImpl(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public Set<BuildingDTO> findAllBuildings() {
        Set<Building> buildings = buildingRepository.findAllWithoutNPlusOne();
        return modelMapper.map(buildings, new TypeToken<Set<BuildingDTO>>() {}.getType());
    }

    @Override
    public BuildingDTO findBuildingByNameOfBuilding(String buildingName) {
        return modelMapper.map(buildingRepository.findByNameOfBuilding(buildingName), BuildingDTO.class);
    }

    @Override
    public void saveBuilding(BuildingDTO building) {
        buildingRepository.save(modelMapper.map(building, Building.class));
    }

    @Override
    public void deleteBuilding(Long id) {
        buildingRepository.deleteById(id);
    }

    @Override
    public BuildingDTO findBuildingById(Long id) {
        return modelMapper.map(buildingRepository.findById(id).orElseThrow(EntityNotFoundException::new), BuildingDTO.class);
    }

    @Override
    public BuildingDTO getOneById(Long id) {
        return modelMapper.map(buildingRepository.getOne(id), BuildingDTO.class);
    }
}
