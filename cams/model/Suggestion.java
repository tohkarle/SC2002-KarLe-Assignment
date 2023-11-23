package cams.model;

import java.io.Serializable;


/**
 * A model object for all relevant suggestion information
 */
public class Suggestion implements Serializable {
    private int id;
    private String studentName;
    private String title;
    private Camp camp;
    private String processedBy;
    private SuggestionStatus suggestionStatus;

    /**
     * Constructor for new suggestion object
     * @param id The ID of the suggestion
     * @param studentName The name of the student that made the suggestion
     * @param title The title of the suggestion
     * @param camp The camp object the suggestion is for
     */
    public Suggestion(int id, String studentName, String title, Camp camp) {
        this.id = id;
        this.studentName = studentName;
        this.title = title;
        this.camp = camp;
        this.processedBy = null;
        this.suggestionStatus = SuggestionStatus.PENDING;
    }


    /**
     * A method to create a copy of the suggestion object
     * @param other The suggestion object to be copied
     */
    public Suggestion(Suggestion other) {
        this.id = other.id;
        this.studentName = other.studentName;
        this.title = other.title;
        this.camp = new Camp(other.camp);
        this.processedBy = other.processedBy;
        this.suggestionStatus = other.suggestionStatus;
    }

    
    /**
     * Getter of suggestion ID
     * @return int of the suggestion ID
     */
    public int getId() {
        return this.id;
    }


    /**
     * Setter for suggestion ID
     * @param id The new ID
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Getter for the name of student that wrote the suggestion
     * @return String of name of student
     */
    public String getStudentName() {
        return this.studentName;
    }


    /**
     * Setter for name of student that wrote the suggestion
     * @param studentName The new name of the student
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }


    /**
     * Getter for title of the suggestion
     * @return String of title of suggestion
     */
    public String getTitle() {
        return this.title;
    }


    /**
     * Setter for the suggestion title
     * @param title The new suggestion title
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * Getter of name of user who processed the suggestion
     * @return String of name of processor
     */
    public String getProcessedBy() {
        return this.processedBy;
    }


    /**
     * Setter for name of user who processed the suggestion
     * @param staffName The name of staff that processed the suggestion
     */
    public void setProcessedBy(String staffName) {
        this.processedBy = staffName;
    }


    /**
     * Getter for suggestion status
     * @return SuggestionStatus enum object for suggestion status
     */
    public SuggestionStatus getSuggestionStatus() {
        return this.suggestionStatus;
    }


    /**
     * Setter for suggestion status
     * @param suggestionStatus The new suggestion status
     */
    public void setSuggestionStatus(SuggestionStatus suggestionStatus) {
        this.suggestionStatus = suggestionStatus;
    }


    /**
     * Getter for camp object the suggestion is for
     * @return Camp object suggestion is for
     */
    public Camp getCamp() {
        return this.camp;
    }
    

    /**
     * Setter for the Camp the suggestion is for
     * @param camp The Camp object the suggestion is for
     */
    public void setCamp(Camp camp) {
        this.camp = camp;
    }
}

