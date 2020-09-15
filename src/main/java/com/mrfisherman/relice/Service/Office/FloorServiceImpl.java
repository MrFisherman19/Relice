package com.mrfisherman.relice.Service.Office;

import com.mrfisherman.relice.Dto.BuildingDTO;
import com.mrfisherman.relice.Dto.FloorDTO;
import com.mrfisherman.relice.Entity.Property.Building;
import com.mrfisherman.relice.Entity.Property.Floor;
import com.mrfisherman.relice.Repository.BuildingRepository;
import com.mrfisherman.relice.Repository.FloorRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import java.util.Set;

@Service
public class FloorServiceImpl implements FloorService {

    private final FloorRepository floorRepository;
    private final ModelMapper modelMapper;

    public FloorServiceImpl(FloorRepository floorRepository) {
        this.floorRepository = floorRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void saveFloor(FloorDTO floor) {
        floorRepository.save(modelMapper.map(floor, Floor.class));
    }

    @Override
    public void deleteFloor(Long id) {
        floorRepository.deleteById(id);
    }

    @Override
    public FloorDTO findFloorById(Long id) {
        return modelMapper.map(floorRepository.findById(id).orElseThrow(EntityNotFoundException::new), FloorDTO.class);
    }

    @Override
    public FloorDTO getOneById(Long id) {
        return modelMapper.map(floorRepository.getOne(id),FloorDTO.class);
    }

    @Override
    public List<FloorDTO> findAllFloorsByBuildingId(Long buildingId) {
        List<Floor> floors = floorRepository.findAllByBuildingId(buildingId);
        return modelMapper.map(floors, new TypeToken<List<FloorDTO>>() {}.getType());
    }

    @Override
    public List<FloorDTO> findAllFloors() {
        List<Floor> floors = floorRepository.findAllOrderByBuildingAndFloorNumber();
        return modelMapper.map(floors, new TypeToken<List<FloorDTO>>() {}.getType());
    }
}
