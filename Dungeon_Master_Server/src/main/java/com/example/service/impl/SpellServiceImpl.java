package com.example.service.impl;

import com.example.model.SpellEntity;
import com.example.repository.SpellRepository;
import com.example.service.SpellService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpellServiceImpl implements SpellService {
    private final SpellRepository repository;

    public SpellServiceImpl(SpellRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SpellEntity> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<SpellEntity> findById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public Optional<SpellEntity> findByIndex(String index) {
        return this.repository.findByIndex(index);
    }

    @Override
    public List<SpellEntity> findAllByLevel(int level) {
        return this.repository.findAllByLevel(level);
    }

    @Override
    public List<SpellEntity> findAllByDamageTypeIndex(String damageTypeIndex) {
        return this.repository.findAllByDamageTypeIndex(damageTypeIndex);
    }

    @Override
    public List<SpellEntity> findAllBySchoolIndex(String schoolIndex) {
        return this.repository.findAllBySchoolIndex(schoolIndex);
    }


    @Override
    public SpellEntity createSpell(SpellEntity spellEntity) {
        this.repository.save(spellEntity);
        return spellEntity;
    }

    @Override
    public SpellEntity updateSpell(SpellEntity spellEntity, Long id) {
        Optional<SpellEntity> newSpell = this.repository.findById(id);
        SpellEntity spell = null;
        if (newSpell.isPresent()) {
            spell = newSpell.get();
            spell.setIndex(spellEntity.getIndex());
            spell.setName(spellEntity.getName());
            spell.setDescription(spellEntity.getDescription());
            spell.setRange(spellEntity.getRange());
            spell.setMaterial(spellEntity.getMaterial());
            spell.setRitual(spellEntity.isRitual());
            spell.setDuration(spellEntity.getDuration());
            spell.setConcentration(spellEntity.isConcentration());
            spell.setCastingTime(spellEntity.getCastingTime());
            spell.setLevel(spellEntity.getLevel());
            spell.setAttackType(spellEntity.getAttackType());
            spell.setDamageTypeIndex(spellEntity.getDamageTypeIndex());
            spell.setDamageTypeName(spellEntity.getDamageTypeName());
            spell.setSchoolIndex(spellEntity.getSchoolIndex());
            spell.setSchoolName(spellEntity.getSchoolName());
            spell.setCharacterList(spellEntity.getCharacterList());

            this.repository.save(spell);
        }
        return spell;
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }
}
