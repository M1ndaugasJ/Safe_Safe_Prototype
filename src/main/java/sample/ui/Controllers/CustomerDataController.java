package sample.ui.Controllers;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestMethod;import org.springframework.web.bind.annotation.RequestParam;import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import sample.ui.Views.HomeView;
import sample.ui.Views.LoginView;

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
    public ModelAndView login() {
        return new ModelAndView(LoginView.getLoginView(), "command", "name");
    }

    @RequestMapping(value = "/addFileLoggedOn", method = RequestMethod.POST)
    public String addFileLoggedOn(@RequestParam("myFile") MultipartFile file, @RequestParam("email") String email, ModelMap model) {
        if(FileController.isFileValid(file)&&EmailSenderController.isEmailValid(email)){
            EmailSenderController.generateAndSendEmail(email, FileController.convert(file));
            model.addAttribute("name", "File has been uploaded and sent.");
        } else if(!EmailSenderController.isEmailValid(email)){
            model.addAttribute("name", "Please enter correct email address");
        } else if (!FileController.isFileValid(file)){
            model.addAttribute("name", "Please select another file");
        }
        return HomeView.getHomeView();
    }

    @RequestMapping(value = "/addFileLoggedOn", method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView(HomeView.getHomeView(), "command", "name");
    }

}