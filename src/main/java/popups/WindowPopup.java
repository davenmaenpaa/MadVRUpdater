package popups;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class WindowPopup extends Application {


    @Override
    public void start(Stage primaryStage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("MadVR updated!");
        alert.setTitle("MadVR updater");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
