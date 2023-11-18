package cams.model;

import java.io.Serializable;

public class Suggestion implements Serializable {
    private int id;
    private String studentName;
    private String title;
    private Camp camp;
    private String processedBy;
    private SuggestionStatus suggestionStatus;

    public Suggestion(int id, String studentName, String title, Camp camp) {
        this.id = id;
        this.studentName = studentName;
        this.title = title;
        this.camp = camp;
        this.processedBy = null;
        this.suggestionStatus = SuggestionStatus.PENDING;
    }

    public Suggestion(Suggestion other) {
        this.id = other.id;
        this.studentName = other.studentName;
        this.title = other.title;
        this.camp = other.camp;
        this.processedBy = other.processedBy;
        this.suggestionStatus = other.suggestionStatus;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getProcessedBy() {
        return this.processedBy;
    }

    public void setProcessedBy(String staffName) {
        this.processedBy = staffName;
    }

    public SuggestionStatus getSuggestionStatus() {
        return this.suggestionStatus;
    }

    public void setSuggestionStatus(SuggestionStatus suggestionStatus) {
        this.suggestionStatus = suggestionStatus;
    }

    public Camp getCamp() {
        return this.camp;
    }
    
    public void setCamp(Camp camp) {
        this.camp = camp;
    }
}

