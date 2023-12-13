package app.equip;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EquipApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EquipApplication.class.getResource("FXML/crudForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        //to reference the stylesheet to show css style format
        String css = this.getClass().getResource("values/style.css").toExternalForm();
        scene.getStylesheets().add(css);


        stage.setTitle("EQUIP");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}