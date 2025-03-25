package com.example.controller;

import com.example.model.SpellEntity;
import com.example.service.SpellService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SpellController {
    private final SpellService service;

    public SpellController(SpellService service) {
        this.service = service;
    }

    @GetMapping("/spells")
    public ResponseEntity<List<SpellEntity>> findAll() {
        List<SpellEntity> spellEntityList = this.service.findAll();
        if (spellEntityList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(spellEntityList);
    }

    @GetMapping("/spell/{id}")
    public Optional<SpellEntity> findById(@PathVariable Long id) {
        return this.service.findById(id);
    }

    @GetMapping("/spell/index/{index}")
    public ResponseEntity<SpellEntity> findByIndex(@PathVariable String index) {
        return this.service.findByIndex(index)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/spell/level/{level}")
    public ResponseEntity<List<SpellEntity>> findAllByLevel(@PathVariable int level) {
        List<SpellEntity> spellEntityList = this.service.findAllByLevel(level);
        if (spellEntityList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(spellEntityList);
    }

    @GetMapping("/spell/damage_type/{damageType}")
    public ResponseEntity<List<SpellEntity>> findAllByDamageTypeIndex(@PathVariable String damageType) {
        List<SpellEntity> spellEntityList = this.service.findAllByDamageTypeIndex(damageType);
        if (spellEntityList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(spellEntityList);
    }

    @GetMapping("/spell/school/{schoolIndex}")
    public ResponseEntity<List<SpellEntity>> findAllBySchoolIndex(@PathVariable String schoolIndex) {
        List<SpellEntity> spellEntityList = this.service.findAllBySchoolIndex(schoolIndex);
        if (spellEntityList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(spellEntityList);
    }

    @PostMapping("/spell")
    public ResponseEntity<SpellEntity> createSpell(@RequestBody SpellEntity spellEntity) {
        this.service.createSpell(spellEntity);
        return ResponseEntity.ok(spellEntity);
    }

    @PutMapping("/spell/{id}")
    public ResponseEntity<SpellEntity> updateSpell(@RequestBody SpellEntity spellEntity, @PathVariable Long id) {
        this.service.updateSpell(spellEntity, id);
        return ResponseEntity.ok(spellEntity);
    }

    @DeleteMapping("/spell/{id}")
    public ResponseEntity<SpellEntity> deleteById(@PathVariable Long id) {
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
