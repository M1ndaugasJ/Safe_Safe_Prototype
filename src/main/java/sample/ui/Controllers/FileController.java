package sample.ui.Controllers;
import org.springframework.web.multipart.MultipartFile;
import sample.ui.Model.*;

import java.io.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
/**
 * Created by Edvinas on 2015-05-18.
 */
public class FileController {

    private List<sample.ui.Model.File> files = new ArrayList<>();

    public FileController() {
        addInitialValues();
    }

    public static File convert(MultipartFile file)
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

    public static boolean isFileValid(MultipartFile file){
        if (!file.isEmpty()) {
            String name = file.getName();
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
                stream.write(bytes);
                stream.close();
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public void addInitialValues(){
        getFiles().add(new sample.ui.Model.File("wow.jpeg"));
        getFiles().add(new sample.ui.Model.File("muchprototype.exe"));
        getFiles().add(new sample.ui.Model.File("passedexam.webm"));
    }


    public List<sample.ui.Model.File> getFiles() {
        return files;
    }

    public void setFiles(List<sample.ui.Model.File> file) {
        this.files = file;
    }

}
