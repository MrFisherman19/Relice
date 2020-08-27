package com.mrfisherman.relice;

import com.mrfisherman.relice.Entity.Electronic.ElectronicEquipment;
import com.mrfisherman.relice.Entity.Electronic.ElectronicEquipmentType;
import com.mrfisherman.relice.Entity.Furnitures.Desk;
import com.mrfisherman.relice.Entity.Property.Address;
import com.mrfisherman.relice.Entity.Property.Building;
import com.mrfisherman.relice.Entity.Property.Floor;
import com.mrfisherman.relice.Entity.Property.Localization;
import com.mrfisherman.relice.Repository.DeskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ReliceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReliceApplication.class, args);
    }

    @Component
    static class addTestDataToDatabase implements CommandLineRunner {

        final DeskRepository deskRepository;

        public addTestDataToDatabase(DeskRepository deskRepository) {
            this.deskRepository = deskRepository;
        }

        @Override
        public void run(String... args) {
            Address address = new Address();
            address.setCity("Kraków");
            address.setCountry("Poland");
            address.setNumberOnStreet("7i");
            address.setStreetName("Puszkarska");
            address.setZipCode("30-644");

            Building building = new Building();
            building.setAddress(address);
            building.setNumberOfFloors(6);
            building.setNameOfBuilding("Building D");

            Floor floor = new Floor();
            floor.setBuilding(building);
            floor.setFloorNumber(4);

            Desk desk = new Desk();
            Localization localization = new Localization(floor,  2,3);

            ElectronicEquipment electronicEquipment = new ElectronicEquipment();
            electronicEquipment.setClient("Lufthansa");
            electronicEquipment.setAdditionalNote("Nice laptop");
            electronicEquipment.setExternalId("WS-123123");
            electronicEquipment.setType(ElectronicEquipmentType.LAPTOP);
            electronicEquipment.setLocalization(localization);
            electronicEquipment.setDesk(desk);

            ElectronicEquipment electronicEquipment2 = new ElectronicEquipment();
            electronicEquipment2.setClient("LSG");
            electronicEquipment2.setAdditionalNote("Not good PC");
            electronicEquipment2.setExternalId("WS-125125");
            electronicEquipment2.setType(ElectronicEquipmentType.PC);
            electronicEquipment2.setLocalization(localization);
            electronicEquipment2.setDesk(desk);

            desk.setLocalization(localization);
            desk.setDeskNumber("4-123");

            Set<ElectronicEquipment> electronicEquipmentSet = new HashSet<>();
            electronicEquipmentSet.add(electronicEquipment);
            electronicEquipmentSet.add(electronicEquipment2);
            desk.setElectronicEquipments(electronicEquipmentSet);
            desk.setAdditionalNote("Nice desk");

            deskRepository.save(desk);
        }
    }
}
