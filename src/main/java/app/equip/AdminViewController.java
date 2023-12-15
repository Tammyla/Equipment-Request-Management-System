package app.equip;

import app.lib.DbConnection;
import app.lib.Inventory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AdminViewController implements Initializable {

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private Alert alert;

    //private String[] yearList = {"1st Year","",""};

    public ObservableList<Inventory> equipmentListData(){
        ObservableList<Inventory> listData = FXCollections.observableArrayList();

        String selectData = "SELECT * FROM tb_inventory";

        connect = DbConnection.connect();

        try{
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            Inventory eData;

            while(result.next()){
                eData = new Inventory(result.getInt("id"), result.getString("name"), result.getInt("type_id"), result.getInt("availability_id"));
                listData.add(eData);
            }

        } catch (Exception e){e.printStackTrace();}
        return listData;
    }

    private ObservableList<Inventory> inventoryData;
    public void equipmentShowData(){
        inventoryData = equipmentListData();

        colEquipmentID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEquipmentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEquipmentType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colEquipmentAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));

        tblEquipmentView.setItems(inventoryData);
    }

    public void equipmentSelectData(){
        Inventory eData = tblEquipmentView.getSelectionModel().getSelectedItem();
        int num = tblEquipmentView.getSelectionModel().getSelectedIndex();

        if((num - 1) < -1)return;

    }



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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //TODO

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

    //Start of Request FXML Attributes
    @FXML
    private SplitPane requestForm;

    @FXML
    private Button btnRequestAdd;

    @FXML
    private Button btnRequestClear;

    @FXML
    private Button btnRequestDelete;

    @FXML
    private Button btnRequestUpdate;

    @FXML
    private TableView<?> tblRequestView;

    @FXML
    private TableColumn<?, ?> colRequestGivenTime;

    @FXML
    private TableColumn<?, ?> colRequestID;

    @FXML
    private TableColumn<?, ?> colRequestItems;

    @FXML
    private TableColumn<?, ?> colRequestLocation;

    @FXML
    private TableColumn<?, ?> colRequestNotes;

    @FXML
    private TableColumn<?, ?> colRequestReturnTime;

    @FXML
    private TableColumn<?, ?> colRequestSignature;

    @FXML
    private TableColumn<?, ?> colRequestStatus;

    @FXML
    private TableColumn<?, ?> colRequestType;

    @FXML
    private TableColumn<?, ?> colRequestUser;
    //End of Request FXML Attributes


    //Start of User FXML Attributes
    @FXML
    private SplitPane userForm;

    @FXML
    private TextField txtUserEmail;

    @FXML
    private TextField txtUserFName;

    @FXML
    private TextField txtUserID;

    @FXML
    private TextField txtUserLName;

    @FXML
    private TextField txtUserPhoneNo;

    @FXML
    private ComboBox<?> cbxUserRole;

    @FXML
    private Button btnUserAdd;

    @FXML
    private Button btnUserClear;

    @FXML
    private Button btnUserDelete;

    @FXML
    private Button btnUserUpdate;

    @FXML
    private TableView<?> tblUserView;

    @FXML
    private TableColumn<?, ?> colUserEmail;

    @FXML
    private TableColumn<?, ?> colUserFName;

    @FXML
    private TableColumn<?, ?> colUserID;

    @FXML
    private TableColumn<?, ?> colUserLName;

    @FXML
    private TableColumn<?, ?> colUserPhoneNo;

    @FXML
    private TableColumn<?, ?> colUserRole;

    //End of User FXML Attributes


    //Start of Equipment FXML Attributes
    @FXML
    private SplitPane equipmentForm;

    @FXML
    private TextField txtEquipmentID;

    @FXML
    private TextField txtEquipmentName;

    @FXML
    private ComboBox<?> cbxEquipmentType;

    @FXML
    private ComboBox<?> cbxEquipmentAvailability;

    @FXML
    private Button btnEquipmentAdd;

    @FXML
    private Button btnEquipmentClear;

    @FXML
    private Button btnEquipmentDelete;

    @FXML
    private Button btnEquipmentUpdate;

    @FXML
    private TableView<Inventory> tblEquipmentView;

    @FXML
    private TableColumn<Inventory, String> colEquipmentAvailability;

    @FXML
    private TableColumn<Inventory, String> colEquipmentID;

    @FXML
    private TableColumn<Inventory, String> colEquipmentName;

    @FXML
    private TableColumn<Inventory, String> colEquipmentType;

    //End of Equipment FXML Attributes


}
