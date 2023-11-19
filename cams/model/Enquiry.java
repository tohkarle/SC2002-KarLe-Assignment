package cams.model;

import java.io.Serializable;

public class Enquiry implements Serializable {
    private int id;
    private int campID;
    private String studentName;
    private String title;
    private String content;
    private String reply;
    private Boolean isResolved;
    private String resolvedBy;

    public Enquiry(int id, String studentName, int campID, String title, String content) {

        this.id = id;
        this.campID = campID;
        this.studentName = studentName;
        this.title = title;
        this.content = content;
        this.reply = null;
        this.isResolved = false;
        this.resolvedBy = null;
    }

    public Enquiry(Enquiry other) {
        this.id = other.id;
        this.campID = other.campID;
        this.studentName = other.studentName;
        this.title = other.title;
        this.content = other.content;
        this.reply = other.reply;
        this.isResolved = other.isResolved;
        this.resolvedBy = other.resolvedBy;
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

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Boolean getIsResolved() {
        return this.isResolved;
    }

    public void setIsResolved(boolean isResolved) {
        this.isResolved = isResolved;
    }

    public String getResolvedBy() {
        return this.resolvedBy;
    }

    public void setResolvedBy(String userName) {
        this.resolvedBy = userName;
    }
}
