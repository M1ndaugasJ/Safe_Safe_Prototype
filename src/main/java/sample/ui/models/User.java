package sample.ui.models;
/**
 * Created by Edvinas on 2015-05-17.
 */
public abstract  class User  {
    public String email;
    static int UNIQUE_ID = 0;
    int id = ++UNIQUE_ID;

    public User(Integer id) {
        this.id = id;

    }
    public int hashCode() {
        return id;
    }
}
