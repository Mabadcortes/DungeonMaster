package com.example.repository;

import com.example.model.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipmentRepository extends JpaRepository<EquipmentEntity, Long> {
    public List<EquipmentEntity> findAllByEquipmentCategoryIndex(String equipmentCategoryIndex);

    public Optional<EquipmentEntity> findByIndex(String index);

    public List<EquipmentEntity> findAllByGearCategoryIndex(String gearCategoryIndex);
}
