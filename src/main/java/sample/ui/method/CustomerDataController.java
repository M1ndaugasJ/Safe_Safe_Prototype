package sample.ui.method;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestMethod;import org.springframework.web.bind.annotation.RequestParam;import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;import java.lang.Exception;import java.lang.String;

/**
 * Created by Mindaugo on 2015-05-13.
 */
@Controller
public class CustomerDataController {

    //@ResponseBody ??

    @RequestMapping(value = "/addFile", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("myFile") MultipartFile file, @RequestParam("email") String email, ModelMap model) {
        if (!file.isEmpty()) {
            String name = file.getName();
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
                stream.write(bytes);
                stream.close();
                model.addAttribute("name", "Uploaded");
                return "home";
            } catch (Exception e) {
                model.addAttribute("name", "An exception was catched");
                return "home";
            }
        } else {
            model.addAttribute("name", "File you selected is empty");
            return "home";
        }
    }

    @RequestMapping(value = "/addFile", method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView("home", "command", "name");
    }

}
