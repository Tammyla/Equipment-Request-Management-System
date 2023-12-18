package app.lib;

public class Inventory {

    //Attributes
    private Integer id;
    private String name;
    private String type;
    private String availability;

    public Inventory(Integer id, String name, String type, String availability) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.availability = availability;
    }

    public Inventory(String name, String type, String availability) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", availability='" + availability + '\'' +
                '}';
    }
}
