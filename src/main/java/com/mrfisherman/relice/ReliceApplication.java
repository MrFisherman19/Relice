package com.mrfisherman.relice;

import com.mrfisherman.relice.Entity.Asset.AssetConditionState;
import com.mrfisherman.relice.Entity.Asset.AssetEntity;
import com.mrfisherman.relice.Entity.Asset.AssetLocationState;
import com.mrfisherman.relice.Entity.Asset.AssetType;
import com.mrfisherman.relice.Entity.Property.Address;
import com.mrfisherman.relice.Entity.Building.Building;
import com.mrfisherman.relice.Entity.Building.Floor;
import com.mrfisherman.relice.Entity.Property.Localization;
import com.mrfisherman.relice.Entity.User.User;
import com.mrfisherman.relice.Entity.User.UserRole;
import com.mrfisherman.relice.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class ReliceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReliceApplication.class, args);
    }

    @Component
    static class addTestDataToDatabase implements CommandLineRunner {

        final AssetRepository assetRepository;
        final UserRepository userRepository;
        final BuildingRepository buildingRepository;
        final FloorRepository floorRepository;

        public addTestDataToDatabase(AssetRepository assetRepository, UserRepository userRepository,
                                     BuildingRepository buildingRepository, FloorRepository floorRepository) {
            this.assetRepository = assetRepository;
            this.userRepository = userRepository;
            this.buildingRepository = buildingRepository;
            this.floorRepository = floorRepository;
        }

        @Override
        public void run(String... args) {
            AssetConditionState[] furnitureConditionStates = AssetConditionState.values();
            AssetLocationState[] furnitureLocationStates = AssetLocationState.values();
            AssetType[] assetTypes = AssetType.values();
            String[] additionalNotes = {"Nice desk", "Not nice desk", "Corner desk", "Oh wow!"};

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
            building.setName("Building D");

            buildingRepository.save(building);

            for (int i = 1; i < 7; i++) {
                Floor floor = new Floor();
                floor.setName("Floor" + i);
                floor.setBuilding(building);

                floorRepository.save(floor);
                for (int j = 1; j < 200; j++) {
                    AssetEntity assetEntity = new AssetEntity();
                    assetEntity.setPrefix("DES");
                    assetEntity.setAssetType(assetTypes[(int)(Math.random() * assetTypes.length)]);
                    assetEntity.setLocalization(new Localization(floor, 2, 3));
                    assetEntity.setAssetConditionState(furnitureConditionStates[(int) (Math.random() * furnitureConditionStates.length)]);
                    assetEntity.setAssetLocationState(furnitureLocationStates[(int)(Math.random()*furnitureLocationStates.length)]);
                    assetEntity.setAdditionalNote(additionalNotes[(int)(Math.random()*additionalNotes.length)]);
                    assetRepository.save(assetEntity);
                }
            }

            User user = new User();
            user.setId(1L);
            user.setUserRole(UserRole.ROLE_ADMIN);
            user.setEmail("bartex1010.xdxd@gmail.com");
            user.setName("Bartosz");
            user.setPassword("$2y$12$etpCMI2qXcNpq.ux7VYmnuxxa7buKiu2BNRdVV5hZzia9SQ265UWe");
            user.setEnabled(true);
            user.setNonLocked(true);

            userRepository.save(user);
        }
    }
}
