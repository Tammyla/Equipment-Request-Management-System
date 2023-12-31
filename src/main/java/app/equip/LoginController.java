package app.equip;

import app.lib.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    //LET'S CREATE OUT DATABASE AND CONNECT IT TO JAVA / THE APPLICATION
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public String username;

    //CONTROL THE LOGIN OF ACCOUNTS
    public void loginAccount(){
        String sql = "SELECT user_name, user_password FROM tb_login WHERE user_name = ? and user_password = ?";

        connect = DbConnection.connect();

        try{

            Alert alert;
            if(txtLogUsername.getText().isEmpty() || txtLogPassword.getText().isEmpty()){
                //IF THE USERNAME / PASSWORD WAS EMPTY
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else{
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, txtLogUsername.getText());
                prepare.setString(2,txtLogPassword.getText());

                result = prepare.executeQuery();

                if(result.next()){
                    // IF CORRECT USERNAME AND PASSWORD
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login");
                    alert.showAndWait();

                    //TO HIDE THE LOGIN FORM
                    btnLoginLF.getScene().getWindow().hide();

                    //TO DECIDE WHETHER IT SHOULD BE ADMIN OR REQUESTEE
                    Parent root = FXMLLoader.load(getClass().getResource("FXML/admin-view.fxml"));

                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.show();

                } else {
                    // IF INCORRECT USERNAME OR PASSWORD
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect Username/ Password");
                    alert.showAndWait();
                }


            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            //This will close the database connection when not in use by closing the resultSet first
            if (result != null){
                try{
                    result.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (prepare != null){
                try{
                    prepare.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connect != null){
                try{
                    connect.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }

    }

    //CONTROL THE REGISTRATION OF ACCOUNTS
    public void registerAccount(){

        //Create a stored procedure to accompany both INSERT for user and login table
        String sql = "INSERT INTO tb_login (user_name, user_password) VALUES (?,?)";

        connect = DbConnection.connect();

        try{
            Alert alert;
            if(txtSignUsername.getText().isEmpty() || txtSignPassword.getText().isEmpty()){
                //IF THE USERNAME / PASSWORD WAS EMPTY
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                // CHECK IF USERNAME IS ALREADY TAKEN
                String checkData = "SELECT user_name FROM tb_login WHERE user_name = '"
                + txtSignUsername.getText() + "' ";

                prepare = connect.prepareStatement(checkData);
                result = prepare.executeQuery();

                //IF RESULT SQL IS EXECUTE THE QUERY
                if(result.next()){
                    //IF USERNAME IS TAKEN TO NOTIFY THE USER
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(txtSignUsername.getText() +" is already taken");
                    alert.showAndWait();
                } else {
                    //IF PASSWORD IS LESS THAN 8 CHARACTERS SEND AN ALERT
                    if(txtSignPassword.getText().length() < 8){
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Invalid Password, at least 8 characters needed");
                        alert.showAndWait();
                    } else {
                        //IF USERNAME AND PASSWORD IS APPROVED THEN INSERT INTO THE DATABASE
                        prepare = connect.prepareStatement(sql);
                        prepare.setString(1, txtSignUsername.getText());
                        prepare.setString(2, txtSignPassword.getText());

                        prepare.executeUpdate();

                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully created a new Account!");
                        alert.showAndWait();

                        //AFTER THE SIGNUP COMPLETED SWITCH FORM TO LOG IN FORM AND EMPTY THE SIGNUP TEXTFIELD EMPTY
                        login_form.setVisible(true);
                        signup_form.setVisible(false);
                        user_form.setVisible(false);

                        txtSignUsername.setText("");
                        txtSignPassword.setText("");

                        //send information to the class
                    }
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            //This will close the database connection when not in use by closing the resultSet first
            if (result != null){
                try{
                    result.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (prepare != null){
                try{
                    prepare.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connect != null){
                try{
                    connect.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }

    }

    //To place a list in the combo-box
    private String[] roleList = {"Staff","Student"};
    public void userRoleList(){
        List<String> rList = new ArrayList<>();

        for(String data: roleList){
            rList.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(rList);
        cbxRoleUF.setItems(listData);
    }

    //CONTROL THE CREATION OF USER ACCOUNTS
    public void createUserAccount(){

        //Sql Insert statement for tb_login
        String insert = "INSERT INTO tb_user(first_name,last_name,phone_no,email,role_id,login_id,inventory_id) VALUES (?,?,?,?,?,?,?)";

        connect = DbConnection.connect();

        try{
            Alert alert;
            if(txtFNameUF.getText().isEmpty() || txtLNameUF.getText().isEmpty() || txtPhoneNoUF.getText().isEmpty() || txtEmailUF.getText().isEmpty() || cbxRoleUF.getSelectionModel().isEmpty()){
                //IF THE FIELDS WAS EMPTY
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                // CHECK IF USERNAME IS ALREADY TAKEN
                String checkData = "SELECT user_name FROM tb_login WHERE user_name = '"
                        + txtUsernameUF.getText() + "' ";

                prepare = connect.prepareStatement(checkData);
                result = prepare.executeQuery();
                System.out.println(result);

                //IF RESULT SQL IS EXECUTE THE QUERY
                if(result.next()){
                    //IF FIELDS IS APPROVED THEN INSERT INTO THE DATABASE
                    prepare = connect.prepareStatement(insert);
                    prepare.setString(1, txtFNameUF.getText());
                    prepare.setString(2, txtLNameUF.getText());
                    prepare.setString(3, txtPhoneNoUF.getText());
                    prepare.setString(4, txtEmailUF.getText());

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully created a new User Account!");
                    alert.showAndWait();

                    //AFTER THE SIGNUP COMPLETED SWITCH FORM TO LOG IN FORM AND EMPTY THE SIGNUP TEXTFIELD EMPTY
                    login_form.setVisible(true);
                    signup_form.setVisible(false);
                    user_form.setVisible(false);

                    txtFNameUF.setText("");
                    txtLNameUF.setText("");
                    txtPhoneNoUF.setText("");
                    txtEmailUF.setText("");
                    txtUsernameUF.setText("");
                    //cbxRoleUF.setSelectionModel;
                }



            }

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            //This will close the database connection when not in use by closing the resultSet first
            if (result != null){
                try{
                    result.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (prepare != null){
                try{
                    prepare.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connect != null){
                try{
                    connect.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }

    }



    //CONTROLS THE SWITCHING BETWEEN SIGNUP AND LOGIN FORM
    public void switchForm(ActionEvent event){

        if(event.getSource() == btnLoginSF){
            login_form.setVisible(false);
            signup_form.setVisible(false);
            user_form.setVisible(true);
        } else if(event.getSource() == btnRequesteeLF){
            login_form.setVisible(false);
            signup_form.setVisible(true);
            user_form.setVisible(false);
        }else if(event.getSource() == btnSubmitUF){
            login_form.setVisible(true);
            signup_form.setVisible(false);
            user_form.setVisible(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //TODO
        userRoleList();

    }


    //FXML Elements from the fxml file

    @FXML
    private Button btnLoginLF;

    @FXML
    private Button btnLoginSF;

    @FXML
    private Button btnRequesteeLF;

    @FXML
    private Button btnRequesteeSF;

    @FXML
    private Button btnSubmitUF;

    @FXML
    private ComboBox<?> cbxRoleUF;

    @FXML
    private BorderPane login_form;

    @FXML
    private BorderPane signup_form;

    @FXML
    private TextField txtEmailUF;

    @FXML
    private TextField txtFNameUF;

    @FXML
    private TextField txtLNameUF;

    @FXML
    private PasswordField txtLogPassword;

    @FXML
    private TextField txtLogUsername;

    @FXML
    private TextField txtPhoneNoUF;

    @FXML
    private PasswordField txtSignPassword;

    @FXML
    private TextField txtSignUsername;

    @FXML
    private TextField txtUsernameUF;

    @FXML
    private BorderPane user_form;
}
