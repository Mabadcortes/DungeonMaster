package data;

public class EquipmentListData {

    private Long id;
    private String name;
    private String equipmentCategoryName;
    private String gearCategoryName;
    private int quantity;
    private int weight;
    private String unit;

    public EquipmentListData(String name, String equipmentCategoryName, String gearCategoryName, int quantity, int weight, String unit) {
        this.name = name;
        this.equipmentCategoryName = equipmentCategoryName;
        this.gearCategoryName = gearCategoryName;
        this.quantity = quantity;
        this.weight = weight;
        this.unit = unit;
    }

    public EquipmentListData() {
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

    public String getGearCategoryName() {
        return gearCategoryName;
    }

    public void setGearCategoryName(String gearCategoryName) {
        this.gearCategoryName = gearCategoryName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
