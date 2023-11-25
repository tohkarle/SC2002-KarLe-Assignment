package cams.model;


/**
 * A specialized class of the generic user class
 */
public class Student extends User {
    
    /**
     * Deprecated
     */
    private int point;


    /**
     * Constructor for the model
     * @param name The name of the student
     * @param email The email of the student
     * @param password The password
     * @param faculty The faculty / user group
     */
    public Student(String name, String email, String password, String faculty) {
        super(name, email, password, faculty);
        this.point = 0;
    }

    // For creating a defensive copy
    public Student(User other) {
        super(other);
        this.point = 0;
    }

    /**
     * Deprecated
     */
    public int getPoint() {
        return this.point;
    }


    /**
     * Deprecated
     */
    public void addOnePoint() {
        this.point++;
    }
}
