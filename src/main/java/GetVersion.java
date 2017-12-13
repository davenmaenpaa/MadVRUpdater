import javafx.application.Application;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class GetVersion {

    private String readVersionFile() {
            try {
                return Files.lines(Paths.get(Props.getMadvrDir() + "changelog.txt"))
                        .findFirst()
                        .orElse("Could not find file");
            } catch (IOException e) {
                Application.launch(WrongDirectoryPopup.class);
            }

        return null;
    }

    private int parseVersionFromFile(String string) {
        String readFile = string.replace(".", "");

        // Om 0 finns
        if (readFile.substring(1, 2).equals("0"))
            return Integer.valueOf(readFile.substring(2, readFile.length()));

        return Integer.valueOf(readFile.substring(1, readFile.length()));
    }

    public boolean checkIfUpdateNeeded() {
        String stringLatestVersion = getVersionFromPage();
        String stringCurrentVersion = readVersionFile();

        int currentVersion;

        if (stringCurrentVersion != null) {
            currentVersion = parseVersionFromFile(stringCurrentVersion);
            int latestVersion = parseVersionFromWeb(stringLatestVersion);

            if (currentVersion < latestVersion) {
                downloadFile();
                return true;
            }
        }
        return false;
    }

    private String getVersionFromPage() {
        Document doc = null;
        try {
            doc = Jsoup.connect("http://madvr.com/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element html = Objects.requireNonNull(doc).select("p").first();
        String string = html.toString();

        // Tar ut efter v i strängen
        return string.substring(string.indexOf("v"), string.indexOf(":", string.indexOf("v")));
    }

    private int parseVersionFromWeb(String string) {
        string = string.substring(1, string.length());
        string = string.replace(".", "");

        return Integer.valueOf(string);
    }

    private void downloadFile() {
        URL website = null;
        try {
            website = new URL("http://madshi.net/madVR.zip");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        File file = new File(Props.getDownloadFolder() + "madVR.zip");
        try {
            FileUtils.copyURLToFile(Objects.requireNonNull(website), file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
