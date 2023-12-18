package app.lib;

public class User {

    //Attributes
    private int id;
    private String first_name;
    private String last_name;
    private String phone_no;
    private String email;
    private int role_id;
    private int inventory_id;

    public User(int id, String first_name, String last_name, String phone_no, String email, int role_id, int inventory_id) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_no = phone_no;
        this.email = email;
        this.role_id = role_id;
        this.inventory_id = inventory_id;
    }

    public User(String first_name, String last_name, String phone_no, String email) {

        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_no = phone_no;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", phone_no='" + phone_no + '\'' +
                ", email='" + email + '\'' +
                ", role_id=" + role_id +
                ", inventory_id=" + inventory_id +
                '}';
    }
}
