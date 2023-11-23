package cams.model;

import java.io.Serializable;


/**
 * A model object for all relevant enquiry information
 */
public class Enquiry implements Serializable {
    private int id;
    private int campID;
    private String studentName;
    private String title;
    private String content;
    private String reply;
    private Boolean isResolved;
    private String resolvedBy;


    /**
     * Constructor for a new enquiry
     * @param id The ID of the enquiry
     * @param studentName The name of the student making the enquiry
     * @param campID The ID of the camp the enquiry is for
     * @param title The title of the enquiry
     * @param content The enquiry content
     */
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


    /**
     * A method to make a copy of the enquiry object
     * @param other The enquiry object to be copied
     */
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


    /**
     * Getter for the enquiry ID
     * @return int of the enquiry ID
     */
    public int getId() {
        return this.id;
    }


    /**
     * Setter for enquiry ID
     * @param id The new enquiry ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for camp ID enquiry belongs to
     * @return int of camp ID
     */
    public int getCampID() {
        return this.campID;
    }


    /**
     * Setter for camp ID enquiry belongs to
     * @param campID The new camp ID
     */
    public void setCampID(int campID) {
        this.campID = campID;
    }


    /**
     * Getter for the name of the student that wrote the enquiry
     * @return String of the student name
     */
    public String getStudentName() {
        return this.studentName;
    }


    /**
     * Setter for the name of student that wrote the enquiry
     * @param studentName The new student name
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }


    /**
     * Getter for the title
     * @return String of the title
     */
    public String getTitle() {
        return this.title;
    }

    
    /**
     * Setter for the title
     * @param title The new title
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * Getter for the enquiry content
     * @return String of the enquiry content
     */
    public String getContent() {
        return this.content;
    }


    /**
     * Setter for enquiry content
     * @param content The new enquiry content
     */
    public void setContent(String content) {
        this.content = content;
    }


    /**
     * Getter for the reply of the enquiry
     * @return String of the reply
     */
    public String getReply() {
        return this.reply;
    }


    /**
     * Setter for reply for the enquiry
     * @param reply The new reply
     */
    public void setReply(String reply) {
        this.reply = reply;
    }


    /**
     * Getter for whether enquiry is resolved
     * @return boolean of whether enquiry is resolved
     */
    public Boolean getIsResolved() {
        return this.isResolved;
    }


    /**
     * Setter for whether enquiry is resolved
     * @param isResolved The new resolved state
     */
    public void setIsResolved(boolean isResolved) {
        this.isResolved = isResolved;
    }


    /**
     * Getter for the name of user who resolved enquiry
     * @return String of user name
     */
    public String getResolvedBy() {
        return this.resolvedBy;
    }


    /**
     * Setter for name of user who resolved the enquiry
     * @param userName The resolver's name
     */
    public void setResolvedBy(String userName) {
        this.resolvedBy = userName;
    }
}
