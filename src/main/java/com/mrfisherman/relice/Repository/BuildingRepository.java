package com.mrfisherman.relice.Repository;

import com.mrfisherman.relice.Entity.Building.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {

    @Query("SELECT b FROM Building b LEFT " +
            "JOIN FETCH b.floors f")
    Set<Building> findAllWithoutNPlusOne();

    Optional<Building> findByName(String name);
}
