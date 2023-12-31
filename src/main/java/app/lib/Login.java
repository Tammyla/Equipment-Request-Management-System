package app.lib;

public class Login {

    //Atributes
    private int id;
    private String user_name;
    private String user_password;

    public Login(int id, String user_name, String user_password) {
        this.id = id;
        this.user_name = user_name;
        this.user_password = user_password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", user_name='" + user_name + '\'' +
                ", user_password='" + user_password + '\'' +
                '}';
    }
}
