module app.equip {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens app.equip to javafx.fxml;
    exports app.equip;
}