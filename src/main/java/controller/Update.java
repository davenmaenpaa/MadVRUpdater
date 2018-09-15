package controller;

import model.Props;
import model.VersionCheck;

import java.io.*;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Update {
    private Props props;
    private VersionCheck versionCheck;

    public Update(Props props, VersionCheck versionCheck) {
        this.props = props;
        this.versionCheck = versionCheck;
    }

    public void updateIfRequiredAndProcessNotInUse() {
        if(versionCheck.updateRequired() && !processInUse()) {
            versionCheck.downloadFile();
            unZip(props.getDownloadFolder() + "madvr.zip", props.getMadvrDir());
        }
    }

    private boolean processInUse() {
        try {
            Process process = Runtime.getRuntime().exec("tasklist.exe");
            Scanner scanner = new Scanner(new InputStreamReader(process.getInputStream()));

            while (scanner.hasNext()) {
                if(scanner.nextLine().contains("mpc-hc64.exe")) {
                    return true;
                }
            }

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void unZip(String zipFile, String outputFolder) {
        byte[] buffer = new byte[1024];

        try(ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile))) {

            // Create output directory is not exists
            File folder = new File(props.getMadvrDir());


            if (!folder.exists()) {
                folder.mkdir();
            }

            // Get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();

            while (ze != null) {

                String fileName = ze.getName();
                File newFile = new File(outputFolder + File.separator + fileName);

                System.out.println("file unzip : " + newFile.getAbsoluteFile());

                // Create all non exists folders
                // else you will hit FileNotFoundException for compressed folder
                new File(newFile.getParent()).mkdirs();

                FileOutputStream fos = new FileOutputStream(newFile);

                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
                ze = zis.getNextEntry();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}


