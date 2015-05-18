package sample.ui.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Mindaugas on 2015-05-18.
 */
@Entity
@Table(name = "Errors")
public class Error {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String code;

    @NotNull
    private String name;



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
