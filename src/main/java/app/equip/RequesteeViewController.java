package app.equip;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class RequesteeViewController implements Initializable {

    public void setUserInformation(String username){
        lblUsername.setText(username);
    }

    //CONTROLS THE SWITCHING BETWEEN SIGNUP AND LOGIN FORM
    public void switchForm(ActionEvent event){

        if(event.getSource() == btnDashboard){
            dashboardForm.setVisible(true);
            profileForm.setVisible(false);
            requestForm.setVisible(false);
        } else if(event.getSource() == btnProfile){
            dashboardForm.setVisible(false);
            profileForm.setVisible(true);
            requestForm.setVisible(false);
        } else if(event.getSource() == btnRequest){
            dashboardForm.setVisible(false);
            profileForm.setVisible(false);
            requestForm.setVisible(true);
        } else if(event.getSource() == btnSignOut){
            dashboardForm.setVisible(false);
            profileForm.setVisible(false);
            requestForm.setVisible(false);
            pnlRequesteeMenu.setVisible(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //TODO

    }


    //FXML Attributes
    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnProfile;

    @FXML
    private Button btnProfileDelete;

    @FXML
    private Button btnProfileUpdate;

    @FXML
    private Button btnRequest;

    @FXML
    private Button btnRequestConfirm;

    @FXML
    private Button btnRequestDelete;

    @FXML
    private Button btnRequestUpdate;

    @FXML
    private Button btnSignOut;

    @FXML
    private ComboBox<?> cbxGivenTime;

    @FXML
    private ComboBox<?> cbxRequestedType;

    @FXML
    private ComboBox<?> cbxReturnTime;

    @FXML
    private ComboBox<?> cbxRole;

    @FXML
    private AnchorPane dashboardForm;

    @FXML
    private DatePicker dpRequestedDate;

    @FXML
    private Label lblUsername;

    @FXML
    private SplitPane pnlRequesteeMenu;

    @FXML
    private AnchorPane profileForm;

    @FXML
    private AnchorPane requestForm;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFName;

    @FXML
    private TextField txtLName;

    @FXML
    private TextField txtName21;

    @FXML
    private TextField txtNotes;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPhoneNo;

    @FXML
    private TextField txtRequestedItems;

    @FXML
    private TextField txtUsername;


}
