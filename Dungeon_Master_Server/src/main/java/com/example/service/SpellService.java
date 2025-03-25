package com.example.service;

import com.example.model.SpellEntity;

import java.util.List;
import java.util.Optional;

public interface SpellService {
    List<SpellEntity> findAll();

    Optional<SpellEntity> findById(Long id);

    Optional<SpellEntity> findByIndex(String index);

    List<SpellEntity> findAllByLevel(int level);

    List<SpellEntity> findAllByDamageTypeIndex(String damageTypeIndex);

    List<SpellEntity> findAllBySchoolIndex(String schoolIndex);

    SpellEntity createSpell(SpellEntity spellEntity);

    SpellEntity updateSpell(SpellEntity spellEntity, Long id);

    void deleteById(Long id);
}
