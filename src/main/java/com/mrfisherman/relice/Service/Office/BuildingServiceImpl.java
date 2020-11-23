package com.mrfisherman.relice.Service.Office;

import com.mrfisherman.relice.Dto.BuildingDto;
import com.mrfisherman.relice.Entity.Building.Building;
import com.mrfisherman.relice.Repository.BuildingRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository buildingRepository;
    private final ModelMapper modelMapper;

    public BuildingServiceImpl(BuildingRepository buildingRepository, ModelMapper modelMapper) {
        this.buildingRepository = buildingRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BuildingDto> findAllBuildings() {
        List<Building> buildings = buildingRepository.findAll();
        return modelMapper.map(buildings, new TypeToken<List<BuildingDto>>() {}.getType());
    }

    @Override
    public BuildingDto findBuildingByName(String name) {
        return modelMapper.map(buildingRepository.findByName(name), BuildingDto.class);
    }

    @Override
    public Long saveBuilding(BuildingDto building) {
        return buildingRepository.save(modelMapper.map(building, Building.class)).getId();
    }

    @Override
    public void updateBuilding(BuildingDto building) {
        if (building != null) {
            saveBuilding(building);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void deleteBuilding(Long id) {
        if (buildingRepository.existsById(id)) {
            buildingRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("No building with id: " + id);
        }
    }

    @Override
    public BuildingDto findBuildingById(Long id) {
        return modelMapper.map(buildingRepository.findById(id).orElseThrow(EntityNotFoundException::new), BuildingDto.class);
    }

    @Override
    public BuildingDto getOneById(Long id) {
        return modelMapper.map(buildingRepository.getOne(id), BuildingDto.class);
    }
}
