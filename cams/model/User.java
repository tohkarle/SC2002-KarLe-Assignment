package cams.model;
import java.io.Serializable;

public class User implements Serializable {

    private String name;
    private String email;
    private String password;
    private String faculty;
    
    public User(String name, String email, String password, String faculty){
        this.name = name;
        this.email = email;
        this.password = password;
        this.faculty = faculty;
    }

    // For creating defensive copy
    public User(User other) {
        this.name = other.name;
        this.email = other.email;
        this.password = other.password;
        this.faculty = other.faculty;
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public String getFaculty() {
        return this.faculty;
    }

    public Boolean checkPassword(String password){
        return (this.password.equals(password));
    }

    public void setPassword(String password){
        this.password = password;
    }
}
