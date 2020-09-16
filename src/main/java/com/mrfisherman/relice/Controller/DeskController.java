package com.mrfisherman.relice.Controller;

import com.mrfisherman.relice.Dto.DeskDTO;
import com.mrfisherman.relice.Service.Furniture.DeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/furniture")
public class DeskController {

    private final DeskService deskService;

    @Autowired
    public DeskController(DeskService deskService) {
        this.deskService = deskService;
    }

    @GetMapping("/getAllDesks")
    public List<DeskDTO> getAllDesks() {
        return deskService.findAllDesks();
    }

    @PostMapping("/createDesk")
    public ResponseEntity<?> createDesk(@RequestBody DeskDTO desk) {
        deskService.saveDesk(desk);
        return ResponseEntity.ok("Desk successfully created!");
    }

    @GetMapping(value = "/getDesk", params = "id")
    public DeskDTO getDesk(@RequestParam Long id) {
        return deskService.findDeskById(id);
    }

    @GetMapping(value = "/getDesk", params = "deskNumber")
    public DeskDTO getDeskByDeskNumber(@RequestParam String deskNumber) {
        return deskService.findDeskByDeskNumber(deskNumber);
    }

    @PutMapping("/updateDesk")
    public ResponseEntity<?> updateDesk(@RequestBody DeskDTO updatedDesk) {
        DeskDTO desk = deskService.findDeskById(updatedDesk.getId());
        desk.setLocalization(updatedDesk.getLocalization());
        desk.setElectronicEquipments(updatedDesk.getElectronicEquipments());
        desk.setAdditionalNote(updatedDesk.getAdditionalNote());
        desk.setDeskNumber(updatedDesk.getDeskNumber());
        deskService.saveDesk(desk);
        return ResponseEntity.ok("Desk successfully updated!");
    }

    @DeleteMapping("/deleteDesk")
    public ResponseEntity<?> deleteDesk(Long id) {
        deskService.deleteDesk(id);
        return ResponseEntity.ok("Desk successfully deleted!");
    }
}
