package cams.model;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Camp implements Serializable {
    private int id;
    private String campName;
    private ArrayList<LocalDate> dates; // 0: start, 1: end, 2: Register close
    private LocalDate registrationClosingDate;
    private boolean visibility;
    private String userGroup;
    private String location;
    private int totalSlots;
    private int committeeSlots;
    private String description;
    private int staffInCharge;
    private ArrayList<Integer> committeeMemberIDs;
    private ArrayList<Integer> participatingStudentIDs;
    private ArrayList<Integer> withdrawnStudentIDs;
    private ArrayList<Integer> enquiryIDs;
    private ArrayList<Integer> suggestionIDs;

    public Camp(int id, String campName, ArrayList<LocalDate> dates, String faculty, boolean visibility, int staffID, LocalDate registrationClosingDate) {
        // Default values
        this.campName = campName;
        this.dates = dates;
        this.visibility = visibility;
        this.userGroup = faculty;
        this.location = "NTU";
        this.totalSlots = 69;
        this.committeeSlots = 10;
        this.description = "Default description";

        this.id = id;
        this.staffInCharge = staffID;
        this.registrationClosingDate = registrationClosingDate;

        this.committeeMemberIDs = new ArrayList<Integer>();
        this.participatingStudentIDs = new ArrayList<Integer>();
        this.withdrawnStudentIDs = new ArrayList<Integer>();
        this.enquiryIDs = new ArrayList<Integer>();
        this.suggestionIDs = new ArrayList<Integer>();
    }

    
    // For creating a defensive copy of the Camp object
    public Camp(Camp other) {
        this.id = other.id;
        this.campName = other.campName;
        this.dates = new ArrayList<>(other.dates);
        this.registrationClosingDate = other.registrationClosingDate;
        this.visibility = other.visibility;
        this.userGroup = other.userGroup;
        this.location = other.location;
        this.totalSlots = other.totalSlots;
        this.committeeSlots = other.committeeSlots;
        this.description = other.description;
        this.staffInCharge = other.staffInCharge;
        this.committeeMemberIDs = new ArrayList<>(other.committeeMemberIDs);
        this.participatingStudentIDs = new ArrayList<>(other.participatingStudentIDs);
        this.withdrawnStudentIDs = new ArrayList<>(other.withdrawnStudentIDs);
        this.enquiryIDs = new ArrayList<>(other.enquiryIDs);
        this.suggestionIDs = new ArrayList<>(other.suggestionIDs);
    }

    // Getters and setters for attributes
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCampName() {
        return this.campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public LocalDate getStartDate() {
        return this.dates.get(0);
    }

    public LocalDate getEndDate() {
        return this.dates.get(1);
    }

    public ArrayList<LocalDate> getDates() {
        return this.dates;
    }

    public void setDates(ArrayList<LocalDate> dates) {
        this.dates = dates;
    }

    public LocalDate getRegistrationClosingDate() {
        return this.registrationClosingDate;
    }

    public void setRegistrationClosingDate(LocalDate registrationClosingDate) {
        this.registrationClosingDate = registrationClosingDate;
    }

    public boolean getVisibility() {
        return this.visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public String getUserGroup() {
        return this.userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTotalSlots() {
        return this.totalSlots;
    }

    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    public int getCommitteeSlots() {
        return this.committeeSlots;
    }

    public void setCommitteeSlots(int committeeSlots) {
        this.committeeSlots = committeeSlots;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStaffInCharge() {
        return this.staffInCharge;
    }

    public ArrayList<Integer> getParticipatingStudentIDs() {
        return this.participatingStudentIDs;
    }

    public ArrayList<Integer> getWithdrawnStudentIDs() {
        return this.withdrawnStudentIDs;
    }

    public ArrayList<Integer> getCommitteeMemberIDs() {
        return this.committeeMemberIDs;
    }

    public ArrayList<Integer> getEnquiryIDs() {
        return this.enquiryIDs;
    }

    public ArrayList<Integer> getSuggestionIDs() {
        return this.suggestionIDs;
    }
}
