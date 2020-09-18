package com.mrfisherman.relice.Repository;

import com.mrfisherman.relice.Entity.Asset.AssetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface AssetBaseRepository<T extends AssetEntity> extends JpaRepository<T, Long> {

    @Query("SELECT a FROM #{#entityName} a LEFT " +
            "JOIN FETCH a.localization f " +
            "JOIN FETCH f.floor b " +
            "JOIN FETCH b.building ORDER BY a.id DESC")
    List<T> findAllWithoutNPlusOne();

    List<T> findAll();

}