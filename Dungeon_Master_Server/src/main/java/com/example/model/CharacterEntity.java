package com.example.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "character")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CharacterEntity.class)
public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "character_name")
    private String characterName;

    @Column(name = "class")
    private String characterClass;

    @Column(name = "level")
    private int characterLevel;

    @Column(name = "race")
    private String characterRace;

    @Column(name = "background")
    private String characterBackground;

    @Column(name = "alignment")
    private String characterAlignment;

    @Column
    private String skill;

    @Column(name = "experience_points")
    private int experiencePoints;

    @Column(name = "strength")
    private int strength;

    @Column(name = "dexterity")
    private int dexterity;

    @Column(name = "constitution")
    private int constitution;

    @Column(name = "intelligence")
    private int intelligence;

    @Column(name = "wisdom")
    private int wisdom;

    @Column(name = "charisma")
    private int charisma;

    @Column(name = "armor_class")
    private int armorClass;

    @Column(name = "initiative")
    private int initiative;

    @Column(name = "speed")
    private int speed;

    @Column(name = "hit_points")
    private int hitPoints;

    @Column(name = "temporary_hit_points")
    private int temporaryHitPoints;

    @Column(name = "hit_dice")
    private String hitDice;

    @ManyToOne()
    @JoinColumn(name = "users_id")
    private UserEntity playerName;

    @ManyToMany
    @JoinTable(
            name = "rel_character_equipment",
            joinColumns = {@JoinColumn(name = "character_id")},
            inverseJoinColumns = {@JoinColumn(name = "equipment_id")}
    )
    private List<EquipmentEntity> equipmentList;

    @ManyToMany
    @JoinTable(
            name = "rel_character_spell",
            joinColumns = {@JoinColumn(name = "character_id")},
            inverseJoinColumns = {@JoinColumn(name = "spell_id")}
    )
    private List<SpellEntity> spellList;

    public CharacterEntity() {
    }

    public CharacterEntity(Long id, String characterName, String characterClass, int characterLevel, String characterRace, String characterBackground, String characterAlignment, String skill, int experiencePoints, int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma, int armorClass, int initiative, int speed, int hitPoints, int temporaryHitPoints, String hitDice, UserEntity userName, List<EquipmentEntity> equipmentList, List<SpellEntity> spellList) {
        this.id = id;
        this.characterName = characterName;
        this.characterClass = characterClass;
        this.characterLevel = characterLevel;
        this.characterRace = characterRace;
        this.characterBackground = characterBackground;
        this.characterAlignment = characterAlignment;
        this.skill = skill;
        this.experiencePoints = experiencePoints;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
        this.armorClass = armorClass;
        this.initiative = initiative;
        this.speed = speed;
        this.hitPoints = hitPoints;
        this.temporaryHitPoints = temporaryHitPoints;
        this.hitDice = hitDice;
        this.playerName = userName;
        this.equipmentList = equipmentList;
        this.spellList = spellList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    public int getCharacterLevel() {
        return characterLevel;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setCharacterLevel(int characterLevel) {
        this.characterLevel = characterLevel;
    }

    public String getCharacterRace() {
        return characterRace;
    }

    public void setCharacterRace(String characterRace) {
        this.characterRace = characterRace;
    }

    public String getCharacterBackground() {
        return characterBackground;
    }

    public void setCharacterBackground(String characterBackground) {
        this.characterBackground = characterBackground;
    }

    public String getCharacterAlignment() {
        return characterAlignment;
    }

    public void setCharacterAlignment(String characterAlignment) {
        this.characterAlignment = characterAlignment;
    }

    public UserEntity getPlayerName() {
        return playerName;
    }

    public void setPlayerName(UserEntity playerName) {
        this.playerName = playerName;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getTemporaryHitPoints() {
        return temporaryHitPoints;
    }

    public void setTemporaryHitPoints(int temporaryHitPoints) {
        this.temporaryHitPoints = temporaryHitPoints;
    }

    public String getHitDice() {
        return hitDice;
    }

    public List<EquipmentEntity> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<EquipmentEntity> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public void setHitDice(String hitDice) {
        this.hitDice = hitDice;
    }

    public List<SpellEntity> getSpellList() {
        return spellList;
    }

    public void setSpellList(List<SpellEntity> spellList) {
        this.spellList = spellList;
    }

    @Override
    public String toString() {
        return "CharacterEntity{" +
                "id=" + id +
                ", characterName='" + characterName + '\'' +
                ", characterClass='" + characterClass + '\'' +
                ", characterLevel=" + characterLevel +
                ", characterRace='" + characterRace + '\'' +
                ", characterBackground='" + characterBackground + '\'' +
                ", characterAlignment='" + characterAlignment + '\'' +
                ", experiencePoints=" + experiencePoints +
                ", strength=" + strength +
                ", dexterity=" + dexterity +
                ", constitution=" + constitution +
                ", intelligence=" + intelligence +
                ", wisdom=" + wisdom +
                ", charisma=" + charisma +
                ", armorClass=" + armorClass +
                ", initiative=" + initiative +
                ", speed=" + speed +
                ", hitPoints=" + hitPoints +
                ", temporaryHitPoints=" + temporaryHitPoints +
                ", hitDice='" + hitDice + '\'' +
                ", Username=" + playerName +
                ", equipmentList=" + equipmentList +
                ", spellList=" + spellList +
                '}';
    }
}
