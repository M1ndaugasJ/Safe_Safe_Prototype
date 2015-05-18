package sample.ui.Model;

import sample.ui.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * Created by Mindaugo on 2015-05-17.
 */
@Repository
@Table(name = "users")
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
}
