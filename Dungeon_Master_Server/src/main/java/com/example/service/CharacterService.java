package com.example.service;

import com.example.model.CharacterEntity;
import com.example.model.EquipmentEntity;
import com.example.model.SpellEntity;

import java.util.List;
import java.util.Optional;

public interface CharacterService {
    List<CharacterEntity> findAll();

    Optional<CharacterEntity> findById(Long id);

//    Optional<CharacterEntity> getPlayerNameOfCharacter(Long id);

    List<SpellEntity> getSpellsOfCharacter(Long id);

    List<EquipmentEntity> getEquipmentOfCharacter(Long id);

    CharacterEntity createCharacter(CharacterEntity character);

    CharacterEntity updateCharacter(CharacterEntity character, Long id);

    void deleteById(Long id);
}
