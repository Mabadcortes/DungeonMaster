package com.example.controller;

import com.example.model.EquipmentEntity;
import com.example.service.EquipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EquipmentController {

    private final EquipmentService service;

    public EquipmentController(EquipmentService service) {
        this.service = service;
    }

    @GetMapping("/equipments")
    public ResponseEntity<List<EquipmentEntity>> findAll() {
        List<EquipmentEntity> equipmentEntityList = this.service.findAll();
        if (equipmentEntityList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(equipmentEntityList);
    }

    @GetMapping("/equipment/{id}")
    public Optional<EquipmentEntity> findById(@PathVariable Long id) {
        return this.service.findById(id);
    }

    @GetMapping("/equipments/equipment-category-index/{equipmentCategoryIndex}")
    public ResponseEntity<List<EquipmentEntity>> findAllByEquipmentCategoryIndex(@PathVariable String equipmentCategoryIndex) {
        List<EquipmentEntity> equipmentEntityList = this.service.findAllByEquipmentCategoryIndex(equipmentCategoryIndex);
        if (equipmentEntityList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(equipmentEntityList);
    }

    @GetMapping("/equipment/index/{index}")
    public ResponseEntity<EquipmentEntity> findByIndex(@PathVariable String index) {
        return this.service.findByIndex(index)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/equipment/gear-category-index/{gearCategoryIndex}")
    public ResponseEntity<List<EquipmentEntity>> findAllByGearCategoryIndex(@PathVariable String gearCategoryIndex) {
        List<EquipmentEntity> equipmentEntityList = this.service.findAllByGearCategoryIndex(gearCategoryIndex);
        if (equipmentEntityList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(equipmentEntityList);
    }

    @PostMapping("/equipment")
    public ResponseEntity<EquipmentEntity> createEquipment(@RequestBody EquipmentEntity equipmentEntity) {
        this.service.createEquipment(equipmentEntity);
        return ResponseEntity.ok(equipmentEntity);
    }

    @PutMapping("/equipment/{id}")
    public ResponseEntity<EquipmentEntity> updateEquipemnt(@RequestBody EquipmentEntity equipmentEntity, @PathVariable Long id) {
        this.service.updateCharacter(equipmentEntity, id);
        return ResponseEntity.ok(equipmentEntity);
    }

    @DeleteMapping("/equipment/{id}")
    public ResponseEntity<EquipmentEntity> deleteById(@PathVariable Long id) {
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
