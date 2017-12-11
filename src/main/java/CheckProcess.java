import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class CheckProcess {
    static boolean checkProcess() {
        try {
            Process process = Runtime.getRuntime().exec("tasklist.exe");
            Scanner scanner = new Scanner(new InputStreamReader(process.getInputStream()));
            while (scanner.hasNext()) {
                String s = scanner.nextLine();

                if(s.contains("mpc-hc64.exe")) {
                    return true;
                }

            }
            scanner.close();
        } catch (IOException e) {
            //TODO skriv error popup
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        checkProcess();
    }
}
