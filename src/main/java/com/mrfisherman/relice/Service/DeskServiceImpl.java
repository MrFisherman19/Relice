package com.mrfisherman.relice.Service;

import com.mrfisherman.relice.Entity.Furnitures.Desk;
import com.mrfisherman.relice.Dto.DeskDTO;
import com.mrfisherman.relice.Repository.DeskRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.Set;

@Service
public class DeskServiceImpl implements DeskService {

    private final DeskRepository deskRepository;
    private final ModelMapper modelMapper;

    public DeskServiceImpl(DeskRepository deskRepository) {
        this.deskRepository = deskRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public Set<DeskDTO> findAllDesks() {
        Set<Desk> desks = deskRepository.findAllWithoutNPlusOne();
        return modelMapper.map(desks, new TypeToken<Set<DeskDTO>>() {}.getType());
    }

    @Override
    public DeskDTO findDeskByDeskNumber(String deskNumber) {
        return modelMapper.map(deskRepository.findByDeskNumber(deskNumber), DeskDTO.class);
    }

    @Override
    public void saveDesk(DeskDTO desk) {
        deskRepository.save(modelMapper.map(desk, Desk.class));
    }

    @Override
    public void deleteDesk(Long id) {
        deskRepository.deleteById(id);
    }

    @Override
    public DeskDTO findDeskById(Long id) {
        return modelMapper.map(deskRepository.findById(id).orElseThrow(EntityNotFoundException::new), DeskDTO.class);
    }

    @Override
    public DeskDTO getOneById(Long id) {
        return modelMapper.map(deskRepository.getOne(id), DeskDTO.class);
    }
}
