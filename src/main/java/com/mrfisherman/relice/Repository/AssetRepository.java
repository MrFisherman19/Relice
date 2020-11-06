package com.mrfisherman.relice.Repository;

import com.mrfisherman.relice.Entity.Asset.Asset;
import com.mrfisherman.relice.Repository.Projection.AssetConditionStateByAssetType;
import com.mrfisherman.relice.Repository.Projection.AssetConditionStateCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

    @Query("SELECT a FROM #{#entityName} a LEFT " +
            "JOIN FETCH a.localization l " +
            "JOIN FETCH l.floor b " +
            "JOIN FETCH b.building ORDER BY a.id DESC")
    List<Asset> findAllWithoutNPlusOne();

    @Query("SELECT a FROM #{#entityName} a LEFT " +
            "JOIN FETCH a.localization l " +
            "JOIN FETCH l.floor f WHERE f.id = :id")
    Set<Asset> findByFloorId(Long id);

    @Query("SELECT a.assetConditionState as assetConditionState, COUNT(a) as totalCount FROM #{#entityName} a GROUP BY a.assetConditionState")
    List<AssetConditionStateCount> countAssetConditionStates();

    @Query("SELECT a.assetType as assetType, a.assetConditionState as assetConditionState, " +
            "COUNT(a) as totalCount FROM #{#entityName} a GROUP BY a.assetType, a.assetConditionState ORDER BY  a.assetConditionState")
    List<AssetConditionStateByAssetType> countAssetConditionStatesByType();


    List<Asset> findAll();

}