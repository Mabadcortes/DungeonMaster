package com.example.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "spells")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id",scope = SpellEntity.class)
public class SpellEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String index;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String range;

    @Column
    private String material;

    @Column
    private boolean ritual;

    @Column
    private String duration;

    @Column
    private boolean concentration;

    @Column(name = "casting_name")
    private String castingTime;

    @Column
    private int level;

    @Column(name = "atack_type")
    private String attackType;

    @Column(name = "damage_type_index")
    private String damageTypeIndex;

    @Column(name = "damage_type_name")
    private String damageTypeName;

    @Column(name = "school_index")
    private String schoolIndex;

    @Column(name = "school_name")
    private String schoolName;

    @ManyToMany(mappedBy = "spellList",cascade = CascadeType.ALL)
    @JsonIgnore
    List<CharacterEntity> characterList;

    public SpellEntity(Long id, String index, String name, String description, String higherLevel, String range, String material, boolean ritual, String duration, boolean concentration, String castingTime, int level, String attackType, String damageTypeIndex, String damageTypeName, String schoolIndex, String schoolName,List<CharacterEntity> characterList) {
        this.id = id;
        this.index = index;
        this.name = name;
        this.description = description;
        this.range = range;
        this.material = material;
        this.ritual = ritual;
        this.duration = duration;
        this.concentration = concentration;
        this.castingTime = castingTime;
        this.level = level;
        this.attackType = attackType;
        this.damageTypeIndex = damageTypeIndex;
        this.damageTypeName = damageTypeName;
        this.schoolIndex = schoolIndex;
        this.schoolName = schoolName;
        this.characterList = characterList;
    }

    public SpellEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public boolean isRitual() {
        return ritual;
    }

    public void setRitual(boolean ritual) {
        this.ritual = ritual;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isConcentration() {
        return concentration;
    }

    public void setConcentration(boolean concentration) {
        this.concentration = concentration;
    }

    public String getCastingTime() {
        return castingTime;
    }

    public void setCastingTime(String castingTime) {
        this.castingTime = castingTime;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getAttackType() {
        return attackType;
    }

    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }

    public String getDamageTypeIndex() {
        return damageTypeIndex;
    }

    public void setDamageTypeIndex(String damageTypeIndex) {
        this.damageTypeIndex = damageTypeIndex;
    }

    public String getDamageTypeName() {
        return damageTypeName;
    }

    public void setDamageTypeName(String damageTypeName) {
        this.damageTypeName = damageTypeName;
    }

    public String getSchoolIndex() {
        return schoolIndex;
    }

    public void setSchoolIndex(String schoolIndex) {
        this.schoolIndex = schoolIndex;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public List<CharacterEntity> getCharacterList() {
        return characterList;
    }

    public void setCharacterList(List<CharacterEntity> characterList) {
        this.characterList = characterList;
    }

    @Override
    public String toString() {
        return "SpellEntity{" +
                "id=" + id +
                ", index='" + index + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", range='" + range + '\'' +
                ", material='" + material + '\'' +
                ", ritual=" + ritual +
                ", duration='" + duration + '\'' +
                ", concentration=" + concentration +
                ", castingTime='" + castingTime + '\'' +
                ", level=" + level +
                ", attackType='" + attackType + '\'' +
                ", damageTypeIndex='" + damageTypeIndex + '\'' +
                ", damageTypeName='" + damageTypeName + '\'' +
                ", schoolIndex='" + schoolIndex + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", characterList=" + characterList +
                '}';
    }
}
