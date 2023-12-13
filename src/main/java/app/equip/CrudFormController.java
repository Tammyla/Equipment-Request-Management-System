package app.equip;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

public class CrudFormController {

    public void setUserInformation(String username){
        lblUsername.setText(username);
    }




    //CONTROLS THE SWITCHING BETWEEN SIGNUP AND LOGIN FORM
    public void switchForm(ActionEvent event){

        if(event.getSource() == btnDashboard){
            dashoardForm.setVisible(true);
            equipmentForm.setVisible(false);
            requestForm.setVisible(false);
            userForm.setVisible(false);
        } else if(event.getSource() == btnEquipment){
            dashoardForm.setVisible(false);
            equipmentForm.setVisible(true);
            requestForm.setVisible(false);
            userForm.setVisible(false);
        } else if(event.getSource() == btnRequest){
            dashoardForm.setVisible(false);
            equipmentForm.setVisible(false);
            requestForm.setVisible(true);
            userForm.setVisible(false);
        } else if(event.getSource() == btnUser){
            dashoardForm.setVisible(false);
            equipmentForm.setVisible(false);
            requestForm.setVisible(false);
            userForm.setVisible(true);
        } else if(event.getSource() == btnSignOut){
            dashoardForm.setVisible(false);
            equipmentForm.setVisible(false);
            requestForm.setVisible(false);
            userForm.setVisible(false);
            pnAdminMenu.setVisible(false);
        }
    }


    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnEquipment;

    @FXML
    private Button btnRequest;

    @FXML
    private Button btnSignOut;

    @FXML
    private Button btnUser;

    @FXML
    private Label lblUsername;

    @FXML
    private AnchorPane pnAdminMenu;

    @FXML
    private SplitPane dashoardForm;

    @FXML
    private SplitPane equipmentForm;

    @FXML
    private SplitPane requestForm;

    @FXML
    private SplitPane userForm;



}
