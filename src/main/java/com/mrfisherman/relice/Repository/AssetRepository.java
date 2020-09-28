package com.mrfisherman.relice.Repository;

import com.mrfisherman.relice.Entity.Asset.AssetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AssetRepository extends JpaRepository<AssetEntity, Long> {

    @Query("SELECT a FROM #{#entityName} a LEFT " +
            "JOIN FETCH a.localization l " +
            "JOIN FETCH l.floor b " +
            "JOIN FETCH b.building ORDER BY a.id DESC")
    List<AssetEntity> findAllWithoutNPlusOne();

    @Query("SELECT a FROM #{#entityName} a LEFT " +
            "JOIN FETCH a.localization l " +
            "JOIN FETCH l.floor f WHERE f.id = :id")
    Set<AssetEntity> findByFloorId(Long id);

    List<AssetEntity> findAll();

}