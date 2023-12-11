package app.lib;

import app.equip.AdminViewController;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class DBUtils {

    /*Function to control the for when scene are changed to a next one*/
    // add the role variable to function
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username){
        Parent root = null;

        if (username != null){
            //will add a role variable to ensure the differentiation between admin, student and staff
            //using an internal if statement to show the different role see
            try{
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                AdminViewController adminViewController = loader.getController();
                adminViewController.setUserInformation(username);
            } catch (IOException e){
                e.printStackTrace();
            }
        }else {
            try{
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root,600 ,400));
        stage.show();
    }

    /*Function to control the guest registration*/
    public static void guestRegister(ActionEvent event, String name, String phone_no, String email, String role, String username, String password ){
        Connection connection = null;
        PreparedStatement psTnsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/equipdbms", "root","Cherrysql");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM tb_login WHERE user_name = ?");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();

            if(resultSet.isBeforeFirst()){
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username");
                alert.show();
            } else{
                psTnsert = connection.prepareStatement("INSERT INTO tb_login (user_name, user_password) VALUES (?,?,?)");
                psTnsert.setString(1, username);
                psTnsert.setString(2,password);
                psTnsert.executeUpdate();

                changeScene(event, "admin-view.fxml", "Admin Page", username);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            //This will close the database connection when not in use by closing the resultSet first
            if (resultSet != null){
                try{
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null){
                try{
                    psCheckUserExists.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (psTnsert != null){
                try{
                    psTnsert.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }
    }

    /*Function to control the main login*/
    public static void logInUser(ActionEvent event, String username, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/equipdbms", "root","Cherrysql");
            preparedStatement = connection.prepareStatement("SELECT password FROM tb_login WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()){
                System.out.println("User not found in the database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            } else{
                while(resultSet.next()){
                    String retrievedPassword = resultSet.getString("user_password");
                    if (retrievedPassword.equals(password)){
                        changeScene(event,"admin-view.fxml","Admin Page",username);
                    } else{
                        System.out.println("Passwords did not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The provided credentials are incorrect!");
                        alert.show();
                    }
                }
            }

        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            //This will close the database connection when not in use by closing the resultSet first
            if (resultSet != null){
                try{
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null){
                try{
                    preparedStatement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }

    }


}
