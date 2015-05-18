package sample.ui.Controller;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestMethod;import org.springframework.web.bind.annotation.RequestParam;import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Exception;import java.lang.String;

/**
 * Created by Mindaugo on 2015-05-13.
 */
@Controller
public class CustomerDataController {

    //@ResponseBody ??
    private byte[] multipartFile;
    private String email;

    @RequestMapping(value = "/addFile", method = RequestMethod.POST)
    public String addFile(@RequestParam("myFile") MultipartFile file, @RequestParam("email") String email, ModelMap model) {
        if(isFileValid(file)&&isEmailValid(email)){
            EmailSenderController.generateAndSendEmail(getEmail(), convert(file));
            model.addAttribute("name", "File has been uploaded and sent.");
        } else if(!isEmailValid(email)){
            model.addAttribute("name", "Please enter correct email address");
        } else if (!isFileValid(file)){
            model.addAttribute("name", "Please select another file");
        }
        return "login";
    }

    @RequestMapping(value = "/addFile", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("login", "command", "name");
    }

    @RequestMapping(value = "/addFileLoggedOn", method = RequestMethod.POST)
    public String addFileLoggedOn(@RequestParam("myFile") MultipartFile file, @RequestParam("email") String email, ModelMap model) {
        if(isFileValid(file)&&isEmailValid(email)){
            EmailSenderController.generateAndSendEmail(getEmail(), convert(file));
            model.addAttribute("name", "File has been uploaded and sent.");
        } else if(!isEmailValid(email)){
            model.addAttribute("name", "Please enter correct email address");
        } else if (!isFileValid(file)){
            model.addAttribute("name", "Please select another file");
        }
        return "home";
    }

    @RequestMapping(value = "/addFileLoggedOn", method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView("home", "command", "name");
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

    public boolean isEmailValid(String email){
        EmailValidator emailValidator = EmailValidator.getInstance();
        if(emailValidator.isValid(email)){
            setEmail(email);
            return true;
        }
        return false;
    }

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

    public void setMultipartFile(byte[] multipartFile) {
        this.multipartFile = multipartFile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
