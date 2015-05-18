package sample.ui.Controllers;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestMethod;import org.springframework.web.bind.annotation.RequestParam;import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import sample.ui.Views.HomeView;
import sample.ui.Views.LoginView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Exception;import java.lang.String;
import java.util.List;


@Controller
public class FormsController {

    FileController fileController = new FileController();

    @RequestMapping(value = "/addFile", method = RequestMethod.POST)
    public String addFile(@RequestParam("myFile") MultipartFile file, @RequestParam("email") String email, ModelMap model) {
        if(FileController.isFileValid(file)&&EmailSenderController.isEmailValid(email)){
            EmailSenderController.generateAndSendEmail(email, FileController.convert(file));
            model.addAttribute("name", "File has been uploaded and sent.");
        } else if(!EmailSenderController.isEmailValid(email)){
            model.addAttribute("name", "Please enter correct email address");
        } else if (!FileController.isFileValid(file)){
            model.addAttribute("name", "Please select another file");
        }
        return LoginView.getLoginView();
    }

    @RequestMapping(value = "/addFile", method = RequestMethod.GET)
    public ModelAndView loginView() {
        return new ModelAndView(LoginView.getLoginView(), "command", "name");
    }

    @RequestMapping(value = "/addFileLoggedOn", method = RequestMethod.POST)
    public String addFileLoggedOn(@RequestParam("myFile") MultipartFile file, @RequestParam("email") String email, ModelMap model) {
        model.addAttribute("files", fileController.getFiles());
        if(FileController.isFileValid(file)&&EmailSenderController.isEmailValid(email)){
            EmailSenderController.generateAndSendEmail(email, FileController.convert(file));
            fileController.getFiles().add(new sample.ui.Model.File(file.getOriginalFilename()));
            model.addAttribute("name", "File has been uploaded and sent.");
        } else if(!EmailSenderController.isEmailValid(email)){
            model.addAttribute("name", "Please enter correct email address");
        } else if (!FileController.isFileValid(file)){
            model.addAttribute("name", "Please select another file");
        }
        return HomeView.getHomeView();
    }

    @RequestMapping(value ={"/", "/home"}, method = RequestMethod.GET)
    public String homeView(ModelMap model) {
        model.addAttribute("files", fileController.getFiles());
        return HomeView.getHomeView();
    }

    public boolean validateForm(){
        return true;
    }





}
