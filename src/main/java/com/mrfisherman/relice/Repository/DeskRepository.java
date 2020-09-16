package com.mrfisherman.relice.Repository;

import com.mrfisherman.relice.Entity.Furnitures.Desk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface DeskRepository extends JpaRepository<Desk, Long> {

    List<Desk> findAll();

    @Query("SELECT d FROM Desk d LEFT " +
            "JOIN FETCH d.electronicEquipments l " +
            "JOIN FETCH l.localization f " +
            "JOIN FETCH f.floor b " +
            "JOIN FETCH b.building ORDER BY d.id DESC")
    List<Desk> findAllWithoutNPlusOne();

    Desk findByDeskNumber(String descNumber);

}
