package com.mrfisherman.relice.Service.Office;

import com.mrfisherman.relice.Dto.FloorDto;
import com.mrfisherman.relice.Entity.Building.Floor;
import com.mrfisherman.relice.Repository.FloorRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class FloorServiceImpl implements FloorService {

    private final FloorRepository floorRepository;
    private final ModelMapper modelMapper;

    public FloorServiceImpl(FloorRepository floorRepository, ModelMapper modelMapper) {
        this.floorRepository = floorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long saveFloor(FloorDto floor) {
        return floorRepository.save(modelMapper.map(floor, Floor.class)).getId();
    }

    @Override
    public void deleteFloor(Long id) {
        if (floorRepository.existsById(id)) {
            floorRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("No floor with id: " + id);
        }
    }

    @Override
    public void updateFloor(FloorDto floor) {
        Optional.ofNullable(floor).ifPresentOrElse(this::saveFloor, () -> {throw new IllegalArgumentException();});
    }

    @Override
    public FloorDto findFloorById(Long id) {
        return modelMapper.map(floorRepository.findById(id).orElseThrow(EntityNotFoundException::new), FloorDto.class);
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
