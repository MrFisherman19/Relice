package com.mrfisherman.relice.Service.Furniture;

import com.mrfisherman.relice.Dto.DeskDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeskService {

    void saveDesk(DeskDTO desk);

    void deleteDesk(Long id);

    DeskDTO findDeskById(Long id);

    DeskDTO getOneById(Long id);

    DeskDTO findDeskByDeskNumber(String deskNumber);

    List<DeskDTO> findAllDesks();

}