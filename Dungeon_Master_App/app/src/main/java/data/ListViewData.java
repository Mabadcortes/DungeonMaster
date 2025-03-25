package data;

public class ListViewData {
    private String name;
    private Long id;
    private String characterClass;
    private int level;
    private String race;

    public ListViewData(String name, Long id, String characterClass, int level, String race) {
        this.name = name;
        this.id = id;
        this.characterClass = characterClass;
        this.level = level;
        this.race = race;
    }

    public ListViewData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }
}
