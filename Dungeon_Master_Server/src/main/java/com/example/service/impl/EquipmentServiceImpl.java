package com.example.service.impl;

import com.example.model.EquipmentEntity;
import com.example.repository.EquipmentRepository;
import com.example.service.EquipmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository repository;

    public EquipmentServiceImpl(EquipmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EquipmentEntity> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<EquipmentEntity> findById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public List<EquipmentEntity> findAllByEquipmentCategoryIndex(String equipmentCategoryIndex) {
        return this.repository.findAllByEquipmentCategoryIndex(equipmentCategoryIndex);
    }

    @Override
    public Optional<EquipmentEntity> findByIndex(String index) {
        return this.repository.findByIndex(index);
    }

    @Override
    public List<EquipmentEntity> findAllByGearCategoryIndex(String gearCategoryIndex) {
        return this.repository.findAllByGearCategoryIndex(gearCategoryIndex);
    }

    @Override
    public EquipmentEntity createEquipment(EquipmentEntity equipmentEntity) {
        this.repository.save(equipmentEntity);
        return equipmentEntity;
    }

    @Override
    public EquipmentEntity updateCharacter(EquipmentEntity equipmentEntity, Long id) {
        Optional<EquipmentEntity> newEquipment = this.repository.findById(id);
        EquipmentEntity equipment = null;
        if (newEquipment.isPresent()) {
            equipment = newEquipment.get();
            equipment.setIndex(equipmentEntity.getIndex());
            equipment.setName(equipmentEntity.getName());
            equipment.setEquipmentCategoryIndex(equipmentEntity.getEquipmentCategoryIndex());
            equipment.setEquipmentCategoryName(equipmentEntity.getEquipmentCategoryName());
            equipment.setGearCategoryIndex(equipmentEntity.getGearCategoryIndex());
            equipment.setGearCategoryName(equipmentEntity.getGearCategoryName());
            equipment.setQuantity(equipmentEntity.getQuantity());
            equipment.setCostUnit(equipmentEntity.getCostUnit());
            equipment.setWeight(equipmentEntity.getWeight());
            equipment.setCharacterList(equipmentEntity.getCharacterList());

            this.repository.save(equipment);
        }
        return equipment;
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }
}
