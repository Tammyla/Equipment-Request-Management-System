package app.equip;

import app.lib.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminViewController implements Initializable {

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnInventory;

    @FXML
    private Button btnRequest;

    @FXML
    private Button btnSignOut;

    @FXML
    private Button btnUser;

    @FXML
    private Label lblUsername;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //controls the admin logout using the DBUtils class to carry back to login page
        btnSignOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "login-view.fxml", "Login Page",null);
            }
        });

    }

    public void setUserInformation(String username){
        lblUsername.setText(username);
    }


}
