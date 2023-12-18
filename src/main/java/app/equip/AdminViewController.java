package app.equip;

import app.lib.DbConnection;
import app.lib.Inventory;
import app.lib.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminViewController implements Initializable {

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private Alert alert;


    //Start of Equipment Form List

    //To place a list in the combo-box
    private String[] availabilityList = {"Available","Unavailable"};
    public void eqiAvailabilityList(){
        List<String> aList = new ArrayList<>();

        for(String data: availabilityList){
            aList.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(aList);
        cbxEquipmentAvailability.setItems(listData);
    }

    private String[] typeList = {"Laptop","Accessories","Lab","Speaker","Projector"};
    public void eqiTypeList(){
        List<String> tList = new ArrayList<>();

        for(String data: typeList){
            tList.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(tList);
        cbxEquipmentType.setItems(listData);
    }

    public void equipmentAddBtn(){

        connect = DbConnection.connect();

        try{
            if(txtEquipmentName.getText().isEmpty() || cbxEquipmentType.getSelectionModel().getSelectedItem() == null ||cbxEquipmentAvailability.getSelectionModel().getSelectedItem() == null){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                String checkData = "SELECT id FROM tb_inventory WHERE id = '" + txtEquipmentID.getText() + "' " ;

                prepare = connect.prepareStatement(checkData);
                result = prepare.executeQuery();

                if(result.next()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Equipment ID: " + txtEquipmentID.getText() + "is already taken");
                    alert.showAndWait();
                } else {
                    String insertData = "INSERT INTO tb_inventory (name, type, availability)"
                            + "VALUES(?,?,?) ";

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1,txtEquipmentName.getText());
                    prepare.setString(2,(String)cbxEquipmentType.getSelectionModel().getSelectedItem());
                    prepare.setString(3,(String)cbxEquipmentAvailability.getSelectionModel().getSelectedItem());

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added");
                    alert.showAndWait();

                    //TO UPDATE THE TABLEVIEW
                    equipmentShowData();

                    equipmentClearBtn();
                }
            }

        } catch(Exception e){e.printStackTrace();}
    }

    public void equipmentUpdateBtn(){

        connect = DbConnection.connect();

        try{

            if(txtEquipmentID.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in the Equipment ID");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE equipment ID: " + txtEquipmentID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
                    String updateData = "UPDATE tb_inventory SET"
                            + "name = '" + txtEquipmentName.getText()
                            + "', type = '" + cbxEquipmentType.getSelectionModel().getSelectedItem()
                            + "', availability = '" + cbxEquipmentAvailability.getSelectionModel().getSelectedItem()
                            + "' WHERE id = '" + txtEquipmentID.getText() + "' ";

                    prepare = connect.prepareStatement(updateData);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated.");
                    alert.showAndWait();

                    //TO UPDATE THE TABLEVIEW
                    equipmentShowData();

                    equipmentClearBtn();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Update Cancelled.");
                    alert.showAndWait();
                }

            }

        } catch (Exception e){e.printStackTrace();}
    }

    public void equipmentDeleteBtn(){
        connect = DbConnection.connect();

        try{

            if(txtEquipmentID.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in the Equipment ID");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE equipment ID : " + txtEquipmentID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
                    String deleteData = " DELETE FROM tb_inventory WHERE id = '"
                            + txtEquipmentID.getText() + "' ";

                    prepare = connect.prepareStatement(deleteData);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted.");
                    alert.showAndWait();

                    //TO UPDATE THE TABLEVIEW
                    equipmentShowData();

                    equipmentClearBtn();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Deletion Cancelled.");
                    alert.showAndWait();
                }

            }

        } catch (Exception e){e.printStackTrace();}
    }

    public void equipmentClearBtn(){
        txtEquipmentID.setText("");
        txtEquipmentName.setText("");
        cbxEquipmentType.getSelectionModel().clearSelection();
        cbxEquipmentAvailability.getSelectionModel().clearSelection();
    }

    public ObservableList<Inventory> equipmentListData(){
        ObservableList<Inventory> listData = FXCollections.observableArrayList();

        String selectData = "SELECT * FROM tb_inventory";

        connect = DbConnection.connect();

        try{
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            Inventory eData;

            while(result.next()){
                //calling the inventory constructor that doesn't have identified the ID to call data from the database
                eData = new Inventory(result.getString("name"), result.getString("type"), result.getString("availability"));
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

        txtEquipmentID.setText(String.valueOf(eData.getId()));
        txtEquipmentName.setText(eData.getName());

    }

    //End of Equipment Form List


    //Start of User Form List

    //To place a list in the combo-box
    private String[] roleList = {"Admin","Staff","Student"};
    public void userRoleList(){
        List<String> rList = new ArrayList<>();

        for(String data: roleList){
            rList.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(rList);
        cbxUserRole.setItems(listData);
    }

    //TODO need to edit the internal code for user table button
    public void userAddBtn(){

        connect = DbConnection.connect();

        try{
            if(txtEquipmentName.getText().isEmpty() || cbxEquipmentType.getSelectionModel().getSelectedItem() == null ||cbxEquipmentAvailability.getSelectionModel().getSelectedItem() == null){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                String checkData = "SELECT id FROM tb_inventory WHERE id = '" + txtEquipmentID.getText() + "' " ;

                prepare = connect.prepareStatement(checkData);
                result = prepare.executeQuery();

                if(result.next()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Equipment ID: " + txtEquipmentID.getText() + "is already taken");
                    alert.showAndWait();
                } else {
                    String insertData = "INSERT INTO tb_inventory (name, type, availability)"
                            + "VALUES(?,?,?) ";

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1,txtEquipmentName.getText());
                    prepare.setString(2,(String)cbxEquipmentType.getSelectionModel().getSelectedItem());
                    prepare.setString(3,(String)cbxEquipmentAvailability.getSelectionModel().getSelectedItem());

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added");
                    alert.showAndWait();

                    //TO UPDATE THE TABLEVIEW
                    equipmentShowData();

                    equipmentClearBtn();
                }
            }

        } catch(Exception e){e.printStackTrace();}
    }

    public void userUpdateBtn(){

        connect = DbConnection.connect();

        try{

            if(txtEquipmentID.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in the Equipment ID");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE equipment ID: " + txtEquipmentID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
                    String updateData = "UPDATE tb_inventory SET"
                            + "name = '" + txtEquipmentName.getText()
                            + "', type = '" + cbxEquipmentType.getSelectionModel().getSelectedItem()
                            + "', availability = '" + cbxEquipmentAvailability.getSelectionModel().getSelectedItem()
                            + "' WHERE id = '" + txtEquipmentID.getText() + "' ";

                    prepare = connect.prepareStatement(updateData);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated.");
                    alert.showAndWait();

                    //TO UPDATE THE TABLEVIEW
                    equipmentShowData();

                    equipmentClearBtn();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Update Cancelled.");
                    alert.showAndWait();
                }

            }

        } catch (Exception e){e.printStackTrace();}
    }

    public void userDeleteBtn(){
        connect = DbConnection.connect();

        try{

            if(txtEquipmentID.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in the Equipment ID");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE equipment ID : " + txtEquipmentID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
                    String deleteData = " DELETE FROM tb_inventory WHERE id = '"
                            + txtEquipmentID.getText() + "' ";

                    prepare = connect.prepareStatement(deleteData);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted.");
                    alert.showAndWait();

                    //TO UPDATE THE TABLEVIEW
                    equipmentShowData();

                    equipmentClearBtn();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Deletion Cancelled.");
                    alert.showAndWait();
                }

            }

        } catch (Exception e){e.printStackTrace();}
    }

    public void userClearBtn(){
        txtEquipmentID.setText("");
        txtEquipmentName.setText("");
        cbxEquipmentType.getSelectionModel().clearSelection();
        cbxEquipmentAvailability.getSelectionModel().clearSelection();
    }

    public ObservableList<User> userListData(){
        ObservableList<User> listData = FXCollections.observableArrayList();

        String selectData = "SELECT * FROM tb_user";

        connect = DbConnection.connect();

        try{
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            User uData;

            while(result.next()){
                //calling the inventory constructor that doesn't have identified the ID to call data from the database
                uData = new User(result.getString("first_name"), result.getString("last_name"), result.getString("phone_no"), result.getString("email"));
                listData.add(uData);
            }

        } catch (Exception e){e.printStackTrace();}
        return listData;
    }

    private ObservableList<User> userData;
    public void userShowData(){
        userData = userListData();

        colUserID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUserFName.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        colUserLName.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        colUserPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phone_no"));
        colUserEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colUserRole.setCellValueFactory(new PropertyValueFactory<>("role_id"));

        tblUserView.setItems(userData);
    }

    public void userSelectData(){
        User uData = tblUserView.getSelectionModel().getSelectedItem();
        int num = tblUserView.getSelectionModel().getSelectedIndex();

        if((num - 1) < -1)return;

        txtUserID.setText(String.valueOf(uData.getId()));
        txtUserFName.setText(uData.getFirst_name());
        txtUserLName.setText(uData.getLast_name());

    }

    //End of User Form List





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
            try {
                //TO HIDE THE LOGIN FORM
                btnSignOut.getScene().getWindow().hide();

                //UPON CLOSING, OPEN THE LOGIN FORM
                Parent root = FXMLLoader.load(getClass().getResource("FXML/loginview.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.show();
            } catch (Exception e){e.printStackTrace();}

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //TODO
        equipmentShowData();
        userShowData();
        eqiAvailabilityList();
        eqiTypeList();
        userRoleList();

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
    private TableView<User> tblUserView;

    @FXML
    private TableColumn<User, String> colUserEmail;

    @FXML
    private TableColumn<User, String> colUserFName;

    @FXML
    private TableColumn<User, String> colUserID;

    @FXML
    private TableColumn<User, String> colUserLName;

    @FXML
    private TableColumn<User, String> colUserPhoneNo;

    @FXML
    private TableColumn<User, String> colUserRole;

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
