package com.example.service;

import com.example.model.EquipmentEntity;

import java.util.List;
import java.util.Optional;

public interface EquipmentService {
    List<EquipmentEntity> findAll();

    Optional<EquipmentEntity> findById(Long id);

    List<EquipmentEntity> findAllByEquipmentCategoryIndex(String equipmentCategoryIndex);

    Optional<EquipmentEntity> findByIndex(String index);

    List<EquipmentEntity> findAllByGearCategoryIndex(String gearCategoryIndex);

    EquipmentEntity createEquipment(EquipmentEntity equipmentEntity);

    EquipmentEntity updateCharacter(EquipmentEntity equipmentEntity, Long id);

    void deleteById(Long id);
}
