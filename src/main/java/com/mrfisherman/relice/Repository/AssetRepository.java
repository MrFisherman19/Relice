package com.mrfisherman.relice.Repository;

import com.mrfisherman.relice.Entity.Asset.AssetEntity;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AssetRepository extends AssetBaseRepository<AssetEntity> {


}
