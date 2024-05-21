package sa.osama_alharbi.prj.testers.assistance.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@Slf4j
public class ExtensionSlmService {



    public File getExtensionZip(){
        System.out.println("$$ 1");
        File dir = new File("extension/");
        File dirZip = new File("extension/zip/");
        File extensionFile = new File("extension/zip/extension.zip");
        if(!dir.exists()){
            System.out.println("$$ 2");
            dir.mkdirs();
        }
        System.out.println("$$ 3");
        if(!dirZip.exists()){
            System.out.println("$$ 4");
            dirZip.mkdirs();
        }
        System.out.println("$$ 5");
        if(extensionFile.exists()){
            System.out.println("$$ 6");
            try {
                System.out.println("$$ 7");
                FileUtils.delete(extensionFile);
            } catch (IOException e) {
                System.out.println("$$ 8");
                try {
                    FileUtils.forceDelete(extensionFile);
                    System.out.println("$$ 9");
                } catch (IOException ex) {
                    System.out.println("$$ 10");
                    throw new RuntimeException(ex);
                }
            }
        }
        System.out.println("$$ 11");
        File extensionDir = new File("testers_assistance_selenium/src/main/resources/static/extension/");
        System.out.println("extensionDir 1 => "+extensionDir);
        System.out.println("extensionDir 2 => "+extensionDir.exists());
        System.out.println("extensionDir 3 => "+extensionDir.isDirectory());
        System.out.println("extensionDir 4 => "+extensionDir.list().length);
        String[] extensionFiles = extensionDir.list();
        ArrayList<String> listExtensionFile = new ArrayList<>();
        for (String f : extensionFiles) {
            listExtensionFile.add("testers_assistance_selenium/src/main/resources/static/extension/"+f);
        }
        System.out.println("extensionFile1 => "+extensionFile);
        System.out.println("extensionFile1.exists() => "+extensionFile.exists());
        compressMultipleFiles(extensionFile, listExtensionFile);
        System.out.println("extensionFile2 => "+extensionFile);
        System.out.println("extensionFile2.exists() => "+extensionFile.exists());

        return extensionFile;
    }

    private void compressMultipleFiles(File outputZip, List<String> srcFiles){
        System.out.println("$$ 12");
        FileOutputStream fos = null;
        ZipOutputStream zipOut = null;
        try {
            fos = new FileOutputStream(outputZip);
            zipOut = new ZipOutputStream(fos);

            for (String srcFile : srcFiles) {
                File fileToZip = new File(srcFile);
                FileInputStream fis = new FileInputStream(fileToZip);
                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zipOut.putNextEntry(zipEntry);

                byte[] bytes = new byte[1024];
                int length;
                while((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }
                fis.close();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(zipOut != null){
                    zipOut.close();
                }
                if(fos != null){
                    fos.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
