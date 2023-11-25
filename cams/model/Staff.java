package cams.model;

/**
 * A specialized class of the generic user class
 */
public class Staff extends User {

    /**
     * Constructor for staff model
     * @param name The name of the staff
     * @param email The email of the staff
     * @param password The password for the staff account
     * @param faculty The faculty/user group the staff is from
     */
    public Staff(String name, String email, String password, String faculty) {
        super(name, email, password, faculty);
    }

    /**
     * A method to create a copy of a staff object
     * @param other The staff object to be copied
     */
    public Staff(User other) {
        super(other);
    }
}
