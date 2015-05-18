package sample.ui.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sample.ui.Model.*;

import java.io.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileController {
    @Autowired
    FileRepository fileRepository;

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

    public void compressFile(File File){
        Compressor com = new Compressor(File);
        com.zipIt(File.toString());

    }

    public void decompressFile(File file){
        Compressor com = new Compressor(file);
        com.unZipIt(file.toString());
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

    @RequestMapping("/update")
    @ResponseBody
    public String updateFile(long id, String name) {
        try {
            sample.ui.Model.File file = fileRepository.findOne(id);
            file.setName(name);
            fileRepository.save(file);
        }
        catch (Exception ex) {
            return "Error updating file: " + ex.toString();
        }
        return "file succesfully updated!";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String deleteFile(long id) {
        try {
            sample.ui.Model.File file = fileRepository.findOne(id);
            fileRepository.delete(file);
        }
        catch (Exception ex) {
            return "Error deleting file:" + ex.toString();
        }
        return "file succesfully deleted!";
    }

    public List<sample.ui.Model.File> getFiles() {
        return files;
    }

    public sample.ui.Model.File getFile(int id){ return files.get(id);}

    public void setFiles(List<sample.ui.Model.File> file) {
        this.files = file;
    }


}
