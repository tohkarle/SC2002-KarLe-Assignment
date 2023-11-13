package cams.models;

public class Student extends User {

    private int point;

    public Student(int userID, String email, String name,String password, String faculty) {
        super(userID, email, name, password, faculty);
        this.point = 0;
    }

    public int getPoint() {
        return this.point;
    }

    public void addOnePoint() {
        this.point++;
    }
}
