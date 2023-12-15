package app.lib;

public class User {

    //Attributes
    private int id;
    private String name;
    private String phone_no;
    private String email;
    private int role_id;
    private int inventory_id;

    public User(int id, String name, String phone_no, String email, int role_id, int inventory_id) {
        this.id = id;
        this.name = name;
        this.phone_no = phone_no;
        this.email = email;
        this.role_id = role_id;
        this.inventory_id = inventory_id;
    }

    public User(int id, String name, String phone_no, String email) {
        this.id = id;
        this.name = name;
        this.phone_no = phone_no;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone_no='" + phone_no + '\'' +
                ", email='" + email + '\'' +
                ", role_id=" + role_id +
                ", inventory_id=" + inventory_id +
                '}';
    }
}
