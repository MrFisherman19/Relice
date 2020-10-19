package com.mrfisherman.relice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrfisherman.relice.Entity.Asset.*;
import com.mrfisherman.relice.Entity.Property.Address;
import com.mrfisherman.relice.Entity.Building.Building;
import com.mrfisherman.relice.Entity.Building.Floor;
import com.mrfisherman.relice.Entity.Property.Localization;
import com.mrfisherman.relice.Entity.User.User;
import com.mrfisherman.relice.Entity.User.UserRole;
import com.mrfisherman.relice.Repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.util.Random;

@SpringBootApplication
public class ReliceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReliceApplication.class, args);
    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @Component
    static class addTestDataToDatabase implements CommandLineRunner {

        final AssetRepository assetRepository;
        final UserRepository userRepository;
        final BuildingRepository buildingRepository;
        final FloorRepository floorRepository;

        private static final Random random = new Random();

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

            Address address2 = new Address();
            address2.setCity("Kraków");
            address2.setCountry("Poland");
            address2.setNumberOnStreet("5");
            address2.setStreetName("Wadowicka");
            address2.setZipCode("30-744");

            if(buildingRepository.existsById(33L)) {
                buildingRepository.deleteById(33L);
            }
            if(buildingRepository.existsById(34L)) {
                buildingRepository.deleteById(34L);
            }

//            Building building = new Building();
//            building.setAddress(address);
//            building.setOwner("Bonarka offices");
//            building.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/Lufthansa_Global_Business_Services_Krak%C3%B3w_Bonarka.jpg/800px-Lufthansa_Global_Business_Services_Krak%C3%B3w_Bonarka.jpg");
//            building.setName("Building D");
//
//            Building building2 = new Building();
//            building2.setAddress(address);
//            building2.setOwner("Buma offices");
//            building2.setImageUrl("https://pliki.propertynews.pl/i/05/98/96/059896_940.jpg");
//            building2.setName("Building Buma");
//
//            buildingRepository.save(building);
//            buildingRepository.save(building2);
//
//            for (int i = 1; i < 7; i++) {
//
//                Floor floor = new Floor();
//                floor.setName("Floor " + i);
//                floor.setBuilding(building);
//                floorRepository.save(floor);

//                for (int j = 1; j < 100; j++) {
//
//                    AssetEntity assetEntity = new AssetEntity();
//                    assetEntity.setAssetType(assetTypes[(int) (Math.random() * assetTypes.length)]);
//                    AssetMapDetails assetMapDetails = assetEntity.getAssetType().getAssetMapDetails();
//                    assetEntity.setName("Asset " + j);
//                    assetEntity.setLocalization(new Localization(floor, randomIntNumber(1000,0), randomIntNumber(2400,0), assetMapDetails.getDimensions().getDepth()));
//                    assetEntity.setAssetConditionState(furnitureConditionStates[(int) (Math.random() * furnitureConditionStates.length)]);
//                    assetEntity.setAssetLocationState(furnitureLocationStates[(int) (Math.random() * furnitureLocationStates.length)]);
//                    assetEntity.setAdditionalNote(additionalNotes[(int) (Math.random() * additionalNotes.length)]);
//                    assetEntity.setAssetMapDetails(assetMapDetails);
//                    assetRepository.save(assetEntity);
//                }
            //}
//
//            User user = new User();
//            user.setId(1L);
//            user.setUserRole(UserRole.ROLE_ADMIN);
//            user.setEmail("bartex1010.xdxd@gmail.com");
//            user.setName("Bartosz");
//            user.setPassword("$2y$12$etpCMI2qXcNpq.ux7VYmnuxxa7buKiu2BNRdVV5hZzia9SQ265UWe");
//            user.setEnabled(true);
//            user.setNonLocked(true);
//
//            userRepository.save(user);
//
//            User user2 = new User();
//            user2.setId(2L);
//            user2.setUserRole(UserRole.ROLE_USER);
//            user2.setEmail("rybka143@amorki.pl");
//            user2.setName("Bartosz");
//            user2.setPassword("$2y$12$etpCMI2qXcNpq.ux7VYmnuxxa7buKiu2BNRdVV5hZzia9SQ265UWe");
//            user2.setEnabled(true);
//            user2.setNonLocked(true);
//
//            userRepository.save(user2);
        }

        private int randomIntNumber(int rangeMax, int rangeMin) {
            return (int) (random.nextDouble() * rangeMax + rangeMin);
        }
    }
}
