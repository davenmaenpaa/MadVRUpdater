import javafx.application.Application;
import java.util.concurrent.TimeUnit;

public class Program {
    public static void main(String[] args) {
        new Props();

        GetVersion getVersion = new GetVersion();
        UpdateMadVR updateMadVR = new UpdateMadVR();

        try {
            TimeUnit.SECONDS.sleep(Props.getDelay());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(getVersion.checkIfUpdateNeeded()) {
            updateMadVR.unZipIt(Props.getZipFile(), Props.getMadvrdir());
            Application.launch(Popup.class, args);
        }
    }
}
