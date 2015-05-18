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

    @NotNull
    private int status;

    @NotNull
    private String email;

    @ElementCollection
    @CollectionTable(name="File", joinColumns=@JoinColumn(name="id"))
    @Column(name="files")
    private List<File> files;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserStatus() {return this.status;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
