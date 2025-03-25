package com.example.service.impl;

import com.example.model.CharacterEntity;
import com.example.model.EquipmentEntity;
import com.example.model.SpellEntity;
import com.example.repository.CharacterRepository;
import com.example.service.CharacterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository repository;

    public CharacterServiceImpl(CharacterRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CharacterEntity> findAll() {
        return this.repository.findAll();
    }

    public Optional<CharacterEntity> findById(Long id) {
        return this.repository.findById(id);

    }

    @Override
    public List<SpellEntity> getSpellsOfCharacter(Long id) {
        Optional<CharacterEntity> character = this.repository.findById(id);
        CharacterEntity characterEntity = null;
        List<SpellEntity> spellEntityList = null;
        if (character.isPresent()) {
            characterEntity = character.get();
            spellEntityList = characterEntity.getSpellList();
        }
        return spellEntityList;
    }

    @Override
    public List<EquipmentEntity> getEquipmentOfCharacter(Long id) {
        Optional<CharacterEntity> character = this.repository.findById(id);
        CharacterEntity characterEntity = null;
        List<EquipmentEntity> equipmentEntityList = null;
        if (character.isPresent()) {
            characterEntity = character.get();
            equipmentEntityList = characterEntity.getEquipmentList();
        }
        return equipmentEntityList;
    }

//    @Override
//    public Optional<CharacterEntity> getPlayerNameOfCharacter(Long id) {
//        return this.repository.findById(id);
//    }

    @Override
    public CharacterEntity createCharacter(CharacterEntity character) {
        this.repository.save(character);
        return character;
    }

    @Override
    public CharacterEntity updateCharacter(CharacterEntity character, Long id) {
        Optional<CharacterEntity> newCharacter = this.repository.findById(id);
        CharacterEntity characterEntity = null;
        if (newCharacter.isPresent()) {
            characterEntity = newCharacter.get();
            characterEntity.setCharacterName(character.getCharacterName());
            characterEntity.setCharacterClass(character.getCharacterClass());
            characterEntity.setCharacterLevel(character.getCharacterLevel());
            characterEntity.setCharacterRace(character.getCharacterRace());
            characterEntity.setCharacterBackground(character.getCharacterBackground());
            characterEntity.setCharacterAlignment(character.getCharacterAlignment());
            characterEntity.setPlayerName(character.getPlayerName());
            characterEntity.setExperiencePoints(character.getExperiencePoints());
            characterEntity.setStrength(character.getStrength());
            characterEntity.setDexterity(character.getDexterity());
            characterEntity.setConstitution(character.getConstitution());
            characterEntity.setIntelligence(character.getIntelligence());
            characterEntity.setWisdom(character.getWisdom());
            characterEntity.setCharisma(character.getCharisma());
            characterEntity.setArmorClass(character.getArmorClass());
            characterEntity.setInitiative(character.getInitiative());
            characterEntity.setSpeed(character.getSpeed());
            characterEntity.setHitPoints(character.getHitPoints());
            characterEntity.setTemporaryHitPoints(character.getTemporaryHitPoints());
            characterEntity.setHitDice(character.getHitDice());
            characterEntity.setEquipmentList(character.getEquipmentList());
            characterEntity.setPlayerName(character.getPlayerName());
            characterEntity.setSpellList(character.getSpellList());
            characterEntity.setSkill(character.getSkill());

            this.repository.save(characterEntity);
        }
        return characterEntity;
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }
}
