package sample.ui.Model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@javax.transaction.Transactional
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * This method is not implemented and its working code will be
     * automagically generated from its signature by Spring Data JPA.
     *
     * @param email the user email.
     * @return the user having the passed email or null if no user is found.
     */
    public User findByEmail(String email);

} // class UserRepository