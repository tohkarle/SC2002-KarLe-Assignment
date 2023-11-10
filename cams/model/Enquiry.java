package cams.model;

public class Enquiry{
    private int id;
    private int campID;
    private String studentID;
    private String content;
    private String reply;
    private Boolean isResolved;
    private String resolvedBy;

    public Enquiry(int id, String studentID, int campID, String content) {
        this.id = id;
        this.campID = campID;
        this.studentID = studentID;
        this.content = "";
        this.isResolved = false;
        this.resolvedBy = "";
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCampID() {
        return this.campID;
    }

    public void setCampID(int campID) {
        this.campID = campID;
    }

    public String getStudentID() {
        return this.studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReply() {
        return this.reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Boolean isResolved() {
        return this.isResolved;
    }

    public void setResolved() {
        this.isResolved = true;
    }

    public String getResolvedBy() {
        return this.resolvedBy;
    }

    public void setResolvedBy(String userID) {
        this.resolvedBy = userID;
    }
}
