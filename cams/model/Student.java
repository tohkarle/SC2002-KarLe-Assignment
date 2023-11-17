package cams.model;

public class Student extends User {

    private int point;

    public Student(String name, String email, String password, String faculty) {
        super(name, email, password, faculty);
        this.point = 0;
    }

    // For creating a defensive copy
    public Student(User other) {
        super(other);
        this.point = 0;
    }

    public int getPoint() {
        return this.point;
    }

    public void addOnePoint() {
        this.point++;
    }
}
