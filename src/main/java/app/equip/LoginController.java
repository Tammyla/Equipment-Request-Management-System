package app.equip;

import app.lib.database;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button btnLoginLF;

    @FXML
    private Button btnLoginSF;

    @FXML
    private Button btnRequesteeLF;

    @FXML
    private Button btnRequesteeSF;

    @FXML
    private BorderPane login_form;

    @FXML
    private BorderPane signup_form;

    @FXML
    private PasswordField txtLogPassword;

    @FXML
    private TextField txtLogUsername;

    @FXML
    private PasswordField txtSignPassword;

    @FXML
    private TextField txtSignUsername;

    //LET'S CREATE OUT DATABASE AND CONNECT IT TO JAVA / THE APPLICATION
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public void loginAccount(){
        String sql = "SELECT user_name, user_password FROM tb_login WHERE user_name = ? and user_password = ?";

        connect = database.connect();

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
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //TODO
    }
}
