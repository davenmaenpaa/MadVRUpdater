import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Unzip {
    public static void unZipIt(String zipFile, String outputFolder) {
        byte[] buffer = new byte[1024];

        try(ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile))) {

            // Create output directory is not exists
            File folder = new File(Props.getMadvrDir());
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


