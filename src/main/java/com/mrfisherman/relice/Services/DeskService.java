package com.mrfisherman.relice.Services;

import com.mrfisherman.relice.Entity.Furnitures.Desk;
import com.mrfisherman.relice.Pojo.DeskDTO;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface DeskService {

    void saveDesk(DeskDTO desk);

    void deleteDesk(Long id);

    DeskDTO findDeskById(Long id);

    DeskDTO getOneById(Long id);

    DeskDTO findDeskByDeskNumber(String deskNumber);

    Set<DeskDTO> findAllDesks();

}