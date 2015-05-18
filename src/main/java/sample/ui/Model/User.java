package sample.ui.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int status;
    @NotNull
    private String email;

    private User friend;
    private List<Tuple> files;
    @NotNull
    private String name;

    @NotNull
    private String password;

    public User(String email, String name, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User() { }

    public User(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUser(User id){this.friend = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserStatus() {return this.status;}

    public List<Tuple>  getFileList(){ return this.files;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
