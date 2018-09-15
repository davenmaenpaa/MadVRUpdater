package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Props {
    private Properties prop;

    public Props() {
        prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("src/main/resources/file.properties");
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getDownloadFolder() {
        return prop.getProperty("downloadFolder");
    }

    public String getMadvrDir() {
        return prop.getProperty("madvrdir");
    }

    public int getDelay() {
        return Integer.valueOf(prop.getProperty("delay"));
    }
}
