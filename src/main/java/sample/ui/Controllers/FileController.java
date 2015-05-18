package sample.ui.Controllers;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
/**
 * Created by Edvinas on 2015-05-18.
 */
public class FileController {

    private byte[] multipartFile;
    public File convert(MultipartFile file)
    {
        File convFile = new File(file.getOriginalFilename());
        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convFile;
    }

    public boolean isFileValid(MultipartFile file){
        if (!file.isEmpty()) {
            String name = file.getName();
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
                stream.write(bytes);
                stream.close();
                setMultipartFile(bytes);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public void setMultipartFile(byte[] multipartFile) {
        this.multipartFile = multipartFile;
    }
}
