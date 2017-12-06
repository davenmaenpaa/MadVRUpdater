import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class WrongDirectoryPopup extends Application {

    @Override
    public void start(Stage primaryStage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Wrong directory");
        alert.setTitle("MadVR updater");
        alert.setHeaderText(null);

        alert.showAndWait();
    }

}
