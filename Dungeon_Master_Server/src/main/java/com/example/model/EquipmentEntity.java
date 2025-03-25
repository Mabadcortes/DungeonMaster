package com.example.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "equipment")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = EquipmentEntity.class)
public class EquipmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String index;

    @Column
    private String name;

    @Column(name = "equipment_category_name")
    private String equipmentCategoryName;

    @Column(name = "equipment_category_index")
    private String equipmentCategoryIndex;

    @Column(name = "gear_category_index")
    private String gearCategoryIndex;

    @Column(name = "gear_category_name")
    private String gearCategoryName;

    @Column
    private int quantity;

    @Column(name = "cost_unit")
    private String costUnit;

    @Column
    private int weight;

    @ManyToMany(mappedBy = "equipmentList",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CharacterEntity> characterList;

    public EquipmentEntity(Long id, String index, String name, String equipmentCategoryName, String equipmentCategoryIndex, String gearCategoryIndex, String gearCategoryName, int quantity, String costUnit, int weight, List<CharacterEntity> characterList) {
        this.id = id;
        this.index = index;
        this.name = name;
        this.equipmentCategoryName = equipmentCategoryName;
        this.equipmentCategoryIndex = equipmentCategoryIndex;
        this.gearCategoryIndex = gearCategoryIndex;
        this.gearCategoryName = gearCategoryName;
        this.quantity = quantity;
        this.costUnit = costUnit;
        this.weight = weight;
        this.characterList = characterList;
    }

    public EquipmentEntity() {
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

    public String getEquipmentCategoryName() {
        return equipmentCategoryName;
    }

    public void setEquipmentCategoryName(String equipmentCategoryName) {
        this.equipmentCategoryName = equipmentCategoryName;
    }

    public String getEquipmentCategoryIndex() {
        return equipmentCategoryIndex;
    }

    public void setEquipmentCategoryIndex(String equipmentCategoryIndex) {
        this.equipmentCategoryIndex = equipmentCategoryIndex;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCostUnit() {
        return costUnit;
    }

    public void setCostUnit(String costUnit) {
        this.costUnit = costUnit;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getGearCategoryIndex() {
        return gearCategoryIndex;
    }

    public void setGearCategoryIndex(String gearCategoryIndex) {
        this.gearCategoryIndex = gearCategoryIndex;
    }

    public String getGearCategoryName() {
        return gearCategoryName;
    }

    public void setGearCategoryName(String gearCategoryName) {
        this.gearCategoryName = gearCategoryName;
    }

    public List<CharacterEntity> getCharacterList() {
        return characterList;
    }

    public void setCharacterList(List<CharacterEntity> characterList) {
        this.characterList = characterList;
    }

    @Override
    public String toString() {
        return "EquipmentEntity{" +
                "id=" + id +
                ", index='" + index + '\'' +
                ", name='" + name + '\'' +
                ", equipmentCategoryName='" + equipmentCategoryName + '\'' +
                ", equipmentCategoryIndex='" + equipmentCategoryIndex + '\'' +
                ", gearCategoryIndex='" + gearCategoryIndex + '\'' +
                ", gearCategoryName='" + gearCategoryName + '\'' +
                ", quantity=" + quantity +
                ", costUnit='" + costUnit + '\'' +
                ", weight=" + weight +
                ", characterList=" + characterList +
                '}';
    }
}
