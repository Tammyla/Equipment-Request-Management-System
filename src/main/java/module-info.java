module app.equip {
    requires javafx.controls;
    requires javafx.fxml;


    opens app.equip to javafx.fxml;
    exports app.equip;
}