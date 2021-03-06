package com.mrfisherman.relice.Repository;

import com.mrfisherman.relice.Entity.Building.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {

    @Query("SELECT f FROM Floor f LEFT JOIN FETCH f.building b WHERE b.id = :id ORDER BY f.name DESC")
    List<Floor> findAllByBuildingId(Long id);

    @Query("SELECT f FROM Floor f LEFT JOIN FETCH f.building b ORDER BY f.name DESC, b.id")
    List<Floor> findAllOrderByBuildingAndFloorNumber();
}
