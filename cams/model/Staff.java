package cams.model;

public class Staff extends User {

    public Staff(String name, String email, String password, String faculty) {
        super(name, email, password, faculty);
    }

    // For creating a defensive copy
    public Staff(User other) {
        super(other);
    }
}
