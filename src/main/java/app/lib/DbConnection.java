package app.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class represents the Equipment Request Management System.
 * It provides login and register functions using JavaFX with MySQL database connection.
 */
public class DbConnection {

    //public Connection co
    //static reference  to itself
    private static final String USERNAME = "sql3669573";
    private static final String PASSWORD = "fuQGT8Vzcr";
    private static final String URL = "jdbc:mysql://52.8.112.233:3306/sql3669573";
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static final DbConnection instance = new DbConnection();

    //private constructor
    private DbConnection(){
        try {
            Class.forName(DRIVER_CLASS);
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL , USERNAME, PASSWORD);
            System.out.println("Connected to Database");
            return connection;
        }catch (SQLException e){
            throw new RuntimeException("Cannot connect to database", e);
        } finally {
            if (connection != null)
            {
                try
                {
                    connection.close ();
                    System.out.println ("Database connection terminated");
                }
                catch (Exception e) { /* ignore close errors */ }
            }
        }
    }

    public static Connection connect(){
        return instance.createConnection();
    }

}
