module app.equip {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens app.equip to javafx.fxml;
    exports app.equip;
}