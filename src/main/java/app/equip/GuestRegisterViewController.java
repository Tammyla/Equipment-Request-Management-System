package app.equip;

import app.lib.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class GuestRegisterViewController implements Initializable {

    @FXML
    private Button btnGuest;

    @FXML
    private Button btnLogin;

    @FXML
    private ComboBox<?> cbxRoles;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtPhoneNo;

    @FXML
    private TextField txtUsername;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /*btnGuest.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!txtUsername.getText().trim().isEmpty() && !txtPassword.getText().trim().isEmpty()){
                    //DBUtils.guestRegister(event,txtName.getText(),txtPhoneNo.getText(),txtEmail.getText(),cbxRoles.getId(),txtUsername.getText(),txtPassword.getText());
                } else{
                    System.out.println("Please fill in all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to register");
                    alert.show();
                }

            }
        });*/

        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "login-view.fxml", "Login Page", null);
            }
        });

    }
}
