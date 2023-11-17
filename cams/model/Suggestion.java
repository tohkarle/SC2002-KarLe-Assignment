package cams.model;

import java.io.Serializable;

public class Suggestion implements Serializable {
    private int id;
    private int campID;
    private String studentID;
    private String content;
    private String processedBy;
    private SuggestionStatus suggestionStatus;

    public Suggestion(int id, int campID, String studentID, String content) {
        this.id = id;
        this.campID = campID;
        this.studentID = studentID;
        this.content = content;
        this.processedBy = "";
        this.suggestionStatus = SuggestionStatus.PENDING;
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

    public String getProcessedBy() {
        return this.processedBy;
    }

    public void setProcessedBy(String staffID) {
        this.processedBy = staffID;
    }

    public SuggestionStatus getSuggestionStatus() {
        return this.suggestionStatus;
    }

    public void setSuggestionStatus(SuggestionStatus suggestionStatus) {
        this.suggestionStatus = suggestionStatus;
    }
}

