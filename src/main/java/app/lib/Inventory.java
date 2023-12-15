package app.lib;

public class Inventory {

    //Attributes
    private Integer id;
    private String name;
    private Integer type;
    private Integer availability;

    public Inventory(Integer id, String name, Integer type, Integer availability) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.availability = availability;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", availability=" + availability +
                '}';
    }
}
