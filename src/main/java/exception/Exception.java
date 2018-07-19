package exception;

import javafx.application.Application;
import popups.WindowPopup;

public final class Exception extends RuntimeException {

    public Exception() {
        Application.launch(WindowPopup.class);
    }
}
