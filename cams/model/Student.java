package cams.model;

public class Student extends User {

    private int committeeMamberFor;

    public Student(int userID, String email, String name,String password, String faculty) {
        super(userID, email, name, password, faculty);
        this.committeeMamberFor = -1;
    }

    public boolean isACommitteeMember() {
        return (this.committeeMamberFor != -1);
    }

    public int getCommitteeMemberFor() {
        return this.committeeMamberFor;
    }

    public void setCommitteeMemberFor(int campID) {
        this.committeeMamberFor = campID;
    }
}
