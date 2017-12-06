import javafx.application.Application;

public class WrongDirectory  extends Exception {
    public void errorPopup() {
        Application.launch(WrongDirectoryPopup.class);
    }
}
