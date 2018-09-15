import controller.Update;
import model.Props;
import model.VersionCheck;

public class Application {

    public static void main(String[] args) {
        Props props = new Props();
        new Update(props, new VersionCheck(props)).updateIfRequiredAndProcessNotInUse(); //TODO redo this
    }
}
