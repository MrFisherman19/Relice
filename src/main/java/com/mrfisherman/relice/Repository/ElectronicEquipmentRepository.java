package com.mrfisherman.relice.Repository;

import com.mrfisherman.relice.Entity.Electronic.ElectronicEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectronicEquipmentRepository extends JpaRepository<ElectronicEquipment, Long> {


}
