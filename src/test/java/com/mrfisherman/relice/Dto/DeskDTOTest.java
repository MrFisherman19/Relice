package com.mrfisherman.relice.Dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.mrfisherman.relice.Entity.Electronic.ElectronicEquipmentType;
import com.mrfisherman.relice.Entity.Furnitures.FurnitureConditionState;
import com.mrfisherman.relice.Entity.Furnitures.FurnitureLocationState;
import com.mrfisherman.relice.ReliceApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest(classes = ReliceApplication.class)
@RunWith(SpringRunner.class)
public class DeskDTOTest {

    private JacksonTester<DeskDTO> jacksonTester;

    private static final String DESK_NUMBER = "4-124";
    private static final String ADDITIONAL_NOTE = "Very good desk";
    private static LocalizationDTO localization;
    private static Set<ElectronicEquipmentDTO> electronicEquipments;

    private DeskDTO deskDTO;

    @Before
    public void setup() throws ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);

        BuildingMinimalDTO buildingMinimalDTO = new BuildingMinimalDTO();
        buildingMinimalDTO.setBuildingName("Building D");

        FloorDTO floorDTO = new FloorDTO();
        floorDTO.setFloorNumber(4);
        floorDTO.setBuilding(buildingMinimalDTO);

        localization = new LocalizationDTO();
        localization.setFloor(floorDTO);
        localization.setxAxis(5);
        localization.setyAxis(6);

        ElectronicEquipmentDTO electronicEquipment = new ElectronicEquipmentDTO();
        electronicEquipment.setAdditionalNote("Nice laptop");
        electronicEquipment.setType(ElectronicEquipmentType.PC);
        electronicEquipment.setClient("Lufthansa");
        electronicEquipment.setExternalId("WS-1234");
        electronicEquipment.setLocalization(localization);

        electronicEquipments = new HashSet<>();
        electronicEquipments.add(electronicEquipment);

        deskDTO = new DeskDTO(DESK_NUMBER, ADDITIONAL_NOTE, localization, FurnitureConditionState.GOOD_CONDITION.name(), FurnitureLocationState.RIGHT_PLACE.name(), electronicEquipments);
    }

    private <T> T asParsedJson(Object object) throws JsonProcessingException {
        String json = new ObjectMapper().writeValueAsString(object);
        return JsonPath.read(json, "$");
    }

    @Test
    public void setDeskNumberSerializes() throws IOException {
        assertThat(this.jacksonTester.write(deskDTO))
                .extractingJsonPathStringValue("@.deskNumber")
                .isEqualTo(DESK_NUMBER);
    }

    @Test
    public void setAdditionalNoteSerializes() throws IOException {
        assertThat(this.jacksonTester.write(deskDTO))
                .extractingJsonPathStringValue("@.additionalNote")
                .isEqualTo(ADDITIONAL_NOTE);
    }

    @Test
    public void setLocalizationSerializes() throws IOException {
        assertThat(this.jacksonTester.write(deskDTO))
                .extractingJsonPathMapValue("@.localization")
                .isEqualTo(localization);

    }

    @Test
    public void setElectronicEquipmentsSerializes() throws IOException {
        assertThat(this.jacksonTester.write(deskDTO))
                .extractingJsonPathArrayValue("@.electronicEquipments")
                .isEqualTo(electronicEquipments);
    }
}
