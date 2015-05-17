package sample.ui.Facade;

import sample.ui.Model.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by Mindaugas on 2015-05-16.
 */

@Transactional
public interface UserFacade extends CrudRepository<User, Long> {

    public User findByEmail(String email);

}
