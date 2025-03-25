package com.example.repository;

import com.example.model.SpellEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpellRepository extends JpaRepository<SpellEntity, Long> {
    public Optional<SpellEntity> findByIndex(String index);

    public List<SpellEntity> findAllByLevel(int level);

    public List<SpellEntity> findAllByDamageTypeIndex(String damageTypeIndex);

    public List<SpellEntity> findAllBySchoolIndex(String schoolIndex);

}
