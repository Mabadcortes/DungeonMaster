package data;

public class SpellListData {
    private String name;
    private String range;
    private String castingType;
    private String school;
    private String description;

    public SpellListData(String name, String range, String castingType, String school, String description) {
        this.name = name;
        this.range = range;
        this.castingType = castingType;
        this.school = school;
        this.description = description;
    }

    public SpellListData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getCastingType() {
        return castingType;
    }

    public void setCastingType(String castingType) {
        this.castingType = castingType;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
