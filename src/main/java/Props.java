import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Props {
    static private Properties prop;

    public Props() {
        prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("src/main/resources/file.properties");
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static String getDownloadFolder() {
        return prop.getProperty("downloadFolder");
    }

    public static String getMadvrDir() {
        return prop.getProperty("madvrdir");
    }

    public static int getDelay() {
        return Integer.valueOf(prop.getProperty("delay"));
    }
}
