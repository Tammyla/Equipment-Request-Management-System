package app.lib;

import java.sql.Connection;
import java.sql.DriverManager;

public class database {

    private static String USERNAME = "tammy_db23";
    private static String PASSWORD = "bf668f77";
    private static final String URL = "jdbc:mysql://localhost/equipdbms";

    public static Connection connect(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(URL , USERNAME, PASSWORD);
            System.out.println("Connected");
            return connection;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
