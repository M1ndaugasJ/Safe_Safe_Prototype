package sample.ui.Controllers;

import org.hibernate.criterion.CriteriaQuery;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sample.ui.Model.File;
import sample.ui.Model.User;
import sample.ui.Model.UserRepository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/create")
    @ResponseBody
    public String create(String email, String name, String password) {
        try {
            User user = new User(email, name, password);
            userRepository.save(user);
        }
        catch (Exception ex) {
            System.out.println("Error creating the user:");
            return "Error creating the user: " + ex.toString();
        }
        System.out.println("User succesfully created!");
        return "User succesfully created!";
    }

    /**
     * Delete the user having the passed id.
     *
     * @param email The email for the user to delete
     * @return A string describing if the user is succesfully deleted or not.
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(long id) {
        try {
            User user = new User(id);
            userRepository.delete(user);
        }
        catch (Exception ex) {
            return "Error deleting the user:" + ex.toString();
        }
        return "User succesfully deleted!";
    }

    /**
     * Return the id for the user having the passed email.
     *
     * @param email The email to search in the database.
     * @return The user id or a message error if the user is not found.
     */
    @RequestMapping("/get-by-email")
    @ResponseBody
    public String getByEmail(String email) {
        String userId;
        try {
            User user = userRepository.findByEmail(email);
            userId = String.valueOf(user.getId());
        }
        catch (Exception ex) {
            return "User not found";
        }
        return "The user id is: " + userId;
    }

    /**
     * Update the email and the name for the user in the database having the
     * passed id.
     *
     * @param id The id for the user to update.
     * @param email The new email.
     * @param name The new name.
     * @return a string describing if the user is succesfully updated or not.
     */
    @RequestMapping("/update")
    @ResponseBody
    public String updateUser(long id, String email, String name) {
        try {
            User user = userRepository.findOne(id);
            user.setEmail(email);
            user.setName(name);
            userRepository.save(user);
        }
        catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
        return "User succesfully updated!";
    }

    public User getUser(long id){
        try {
            User user = userRepository.findOne(id);
            return user;
        }
        catch (Exception ex) {
            return null;
        }

    }
    public void addUserToReceipientList(long user, long to) throws Exception {
        try {
            User userObj = userRepository.findOne(user);
            User toObj = userRepository.findOne(to);
        }
        catch (Exception ex) {
            throw new Exception("User not found");
        }
    }

    public List<File> getUserFileList(User user){
       CriteriaBuilder criteriaBuilder;
        return getUser(user.getId()).getFiles();
    }

    public int getUserStatus(User user){
        return user.getUserStatus();
    }
}
