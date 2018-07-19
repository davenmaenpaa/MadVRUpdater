import javafx.application.Application;
import popups.UpdatedPopup;

import java.util.concurrent.TimeUnit;

public class Program {
    public static void main(String[] args) {
        new Props();
        VersionCheck versionCheck = new VersionCheck();

        try {
            TimeUnit.SECONDS.sleep(Props.getDelay());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(versionCheck.checkIfUpdateNeeded() && !CheckProcess.isProcessInUse()) {
            Unzip.unZipIt(Props.getDownloadFolder() + "MadVR.zip", Props.getMadvrDir());
            Application.launch(UpdatedPopup.class, args);
        }
    }
}
