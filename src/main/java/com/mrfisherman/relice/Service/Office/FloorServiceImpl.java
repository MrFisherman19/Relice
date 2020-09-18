package com.mrfisherman.relice.Service.Office;

import com.mrfisherman.relice.Dto.FloorDto;
import com.mrfisherman.relice.Entity.Building.Floor;
import com.mrfisherman.relice.Repository.FloorRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class FloorServiceImpl implements FloorService {

    private final FloorRepository floorRepository;
    private final ModelMapper modelMapper;

    public FloorServiceImpl(FloorRepository floorRepository) {
        this.floorRepository = floorRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void saveFloor(FloorDto floor) {
        floorRepository.save(modelMapper.map(floor, Floor.class));
    }

    @Override
    public void deleteFloor(Long id) {
        floorRepository.deleteById(id);
    }

    @Override
    public FloorDto findFloorById(Long id) {
        return modelMapper.map(floorRepository.findById(id).orElseThrow(EntityNotFoundException::new), FloorDto.class);
    }

    @Override
    public FloorDto getOneById(Long id) {
        return modelMapper.map(floorRepository.getOne(id), FloorDto.class);
    }

    @Override
    public List<FloorDto> findAllFloorsByBuildingId(Long buildingId) {
        List<Floor> floors = floorRepository.findAllByBuildingId(buildingId);
        return modelMapper.map(floors, new TypeToken<List<FloorDto>>() {}.getType());
    }

    @Override
    public List<FloorDto> findAllFloors() {
        List<Floor> floors = floorRepository.findAllOrderByBuildingAndFloorNumber();
        return modelMapper.map(floors, new TypeToken<List<FloorDto>>() {}.getType());
    }
}
