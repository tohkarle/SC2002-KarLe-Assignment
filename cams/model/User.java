package cams.model;
import java.io.Serializable;


/**
 * A model object for all relevant user information
 */
public class User implements Serializable {

    private String name;
    private String email;
    private String password;
    private String faculty;
    

    /**
     * Constructor for new user object
     * @param name The name of the user
     * @param email The email of the user
     * @param password The password for the user account
     * @param faculty The faculty/ user group the user is from
     */
    public User(String name, String email, String password, String faculty){
        this.name = name;
        this.email = email;
        this.password = password;
        this.faculty = faculty;
    }

    
    /**
     * Method for creating a copy of the user object
     * @param other The User object to be copied
     */
    public User(User other) {
        this.name = other.name;
        this.email = other.email;
        this.password = other.password;
        this.faculty = other.faculty;
    }


    /**
     * Getter for the email
     * @return String of email
     */
    public String getEmail() {
        return this.email;
    }


    /**
     * Getter for name of user
     * @return String of name
     */
    public String getName() {
        return this.name;
    }


    /**
     * Getter for faculty/ user group
     * @return String of faculty/ user group
     */
    public String getFaculty() {
        return this.faculty;
    }


    /**
     * A method to check if the provided password matches that in the account
     * @param password The password provided that is to be checked
     * @return boolean of whether the provided password matches that stored in the account
     */
    public Boolean passwordMatches(String password){
        return (this.password.equals(password));
    }


    /**
     * Setter for the password
     * @param password The new password
     */
    public void setPassword(String password){
        this.password = password;
    }
}
