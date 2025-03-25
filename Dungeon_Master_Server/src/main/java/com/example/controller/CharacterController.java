package com.example.controller;

import com.example.model.CharacterEntity;
import com.example.model.EquipmentEntity;
import com.example.model.SpellEntity;
import com.example.service.CharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CharacterController {
    private final CharacterService service;

    public CharacterController(CharacterService service) {
        this.service = service;
    }

    @GetMapping("/characters")
    public ResponseEntity<List<CharacterEntity>> findAll() {
        List<CharacterEntity> characters = this.service.findAll();

        if (characters.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(characters);
    }

    @GetMapping("/character/{id}")
    public Optional<CharacterEntity> findByIdCharacter(@PathVariable Long id) {
        return this.service.findById(id);
    }

//    @GetMapping("/character/player_name/{id}")
//    public ResponseEntity<CharacterEntity> findByPlayerName(@PathVariable Long id) {
//        return this.service.getPlayerNameOfCharacter(id)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

    @GetMapping("/character/equipment_list/{id_character}")
    public ResponseEntity<List<EquipmentEntity>> getEquipmentOfCharacter(@PathVariable Long id_character) {
        List<EquipmentEntity> equipmentEntityList = this.service.getEquipmentOfCharacter(id_character);
        if (equipmentEntityList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(equipmentEntityList);
    }

    @GetMapping("/character/spell_list/{id_character}")
    public ResponseEntity<List<SpellEntity>> getSpellsOfCharacter(@PathVariable Long id_character) {
        List<SpellEntity> spellEntityList = this.service.getSpellsOfCharacter(id_character);
        if (spellEntityList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(spellEntityList);
    }

    @PostMapping("/character")
    public ResponseEntity<CharacterEntity> create(@RequestBody CharacterEntity character) {
        this.service.createCharacter(character);
        return ResponseEntity.ok(character);
    }

    @PutMapping("/character/{id}")
    public ResponseEntity<CharacterEntity> update(@RequestBody CharacterEntity character, @PathVariable Long id) {
        this.service.updateCharacter(character, id);
        return ResponseEntity.ok(character);
    }

    @DeleteMapping("/character/{id}")
    public ResponseEntity<CharacterEntity> deleteById(@PathVariable Long id) {
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
