package com.mrfisherman.relice;

import com.mrfisherman.relice.Entity.Electronic.ElectronicEquipment;
import com.mrfisherman.relice.Entity.Electronic.ElectronicEquipmentType;
import com.mrfisherman.relice.Entity.Furnitures.Desk;
import com.mrfisherman.relice.Entity.Furnitures.FurnitureConditionState;
import com.mrfisherman.relice.Entity.Furnitures.FurnitureLocationState;
import com.mrfisherman.relice.Entity.Property.Address;
import com.mrfisherman.relice.Entity.Property.Building;
import com.mrfisherman.relice.Entity.Property.Floor;
import com.mrfisherman.relice.Entity.Property.Localization;
import com.mrfisherman.relice.Entity.User.User;
import com.mrfisherman.relice.Entity.User.UserRole;
import com.mrfisherman.relice.Repository.*;
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
        final UserRepository userRepository;
        final BuildingRepository buildingRepository;
        final FloorRepository floorRepository;
        final ElectronicEquipmentRepository electronicEquipmentRepository;

        public addTestDataToDatabase(DeskRepository deskRepository, UserRepository userRepository, BuildingRepository buildingRepository,
                                     FloorRepository floorRepository, ElectronicEquipmentRepository electronicEquipmentRepository) {
            this.deskRepository = deskRepository;
            this.userRepository = userRepository;
            this.buildingRepository = buildingRepository;
            this.floorRepository = floorRepository;
            this.electronicEquipmentRepository = electronicEquipmentRepository;
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
                building.setOwner("Bonarka offices");
                building.setImageUrl("https://buma.pl/wp-content/uploads/2018/03/dot-office-f-sg-min-min.jpg");
                building.setNameOfBuilding("Building D");

                buildingRepository.save(building);

                for (int i = 1; i < 7; i++) {
                    Floor floor = new Floor();
                    floor.setFloorNumber(i);
                    floor.setBuilding(building);

                    floorRepository.save(floor);

                    Desk desk = new Desk();

                    ElectronicEquipment electronicEquipment = new ElectronicEquipment();
                    electronicEquipment.setClient("Lufthansa");
                    electronicEquipment.setAdditionalNote("Nice laptop");
                    electronicEquipment.setExternalId("WS-1231" + i);
                    electronicEquipment.setType(ElectronicEquipmentType.LAPTOP);
                    electronicEquipment.setLocalization(new Localization(floor, 2, 3));
                    electronicEquipment.setDesk(desk);

                    ElectronicEquipment electronicEquipment2 = new ElectronicEquipment();
                    electronicEquipment2.setClient("LSG");
                    electronicEquipment2.setAdditionalNote("Not good PC");
                    electronicEquipment2.setExternalId("WS-125125");
                    electronicEquipment2.setType(ElectronicEquipmentType.PC);
                    electronicEquipment2.setLocalization(new Localization(floor, 2, 3));
                    electronicEquipment2.setDesk(desk);

                    desk.setLocalization(new Localization(floor, 2, 3));
                    desk.setDeskNumber(i + "-" + (int) Math.pow(i,4));
                    desk.setConditionState(FurnitureConditionState.BROKEN);
                    desk.setLocationState(FurnitureLocationState.TO_RELOCATION);

                    desk.setAdditionalNote("Nice desk");

                    deskRepository.save(desk);

                    electronicEquipmentRepository.save(electronicEquipment);
                    electronicEquipmentRepository.save(electronicEquipment2);
                }
            System.out.println(deskRepository.findAll());
            System.out.println(deskRepository.findAllWithoutNPlusOne());

            User user = new User();
            user.setId(1L);
            user.setUserRole(UserRole.ROLE_ADMIN);
            user.setEmail("bartex1010.xdxd@gmail.com");
            user.setName("Bartosz");
            user.setPassword("$2y$12$etpCMI2qXcNpq.ux7VYmnuxxa7buKiu2BNRdVV5hZzia9SQ265UWe");
            user.setEnabled(true);
            user.setLocked(false);

            userRepository.save(user);
        }
    }
}
