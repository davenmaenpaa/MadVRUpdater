import javafx.application.Application;

import java.util.concurrent.TimeUnit;

public class Program {
    public static void main(String[] args) {
        new Props();

        GetVersion getVersion = new GetVersion();

        try {
            TimeUnit.SECONDS.sleep(Props.getDelay());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(getVersion.checkIfUpdateNeeded() && !CheckProcess.checkProcess()) {
            Unzip.unZipIt(Props.getDownloadFolder() + "MadVR.zip", Props.getMadvrDir());
            Application.launch(Popup.class, args);
        }
    }
}
