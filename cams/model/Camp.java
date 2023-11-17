package cams.model;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Camp implements Serializable {
    private static int nextId = 0;
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
    private String staffInCharge;
    private ArrayList<String> committeeMemberNames;
    private ArrayList<String> participatingStudentNames;
    private ArrayList<String> withdrawnStudentNames;
    private ArrayList<Integer> enquiryIDs;
    private ArrayList<Integer> suggestionIDs;

    public Camp(String campName, ArrayList<LocalDate> dates, String faculty, boolean visibility, String staffInCharge, LocalDate registrationClosingDate) {
        // Default values
        this.id = nextId;
        Camp.nextId++;
        this.campName = campName;
        this.dates = dates;
        this.visibility = visibility;
        this.userGroup = faculty;
        this.location = "NTU";
        this.totalSlots = 69;
        this.committeeSlots = 10;
        this.description = "Default description";

        this.staffInCharge = staffInCharge;
        this.registrationClosingDate = registrationClosingDate;

        this.committeeMemberNames = new ArrayList<String>();
        this.participatingStudentNames = new ArrayList<String>();
        this.withdrawnStudentNames = new ArrayList<String>();
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
        this.committeeMemberNames = new ArrayList<>(other.committeeMemberNames);
        this.participatingStudentNames = new ArrayList<>(other.participatingStudentNames);
        this.withdrawnStudentNames = new ArrayList<>(other.withdrawnStudentNames);
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

    public String getStaffInCharge() {
        return this.staffInCharge;
    }

    public ArrayList<String> getParticipatingStudentNames() {
        return this.participatingStudentNames;
    }

    public ArrayList<String> getWithdrawnStudentNames() {
        return this.withdrawnStudentNames;
    }

    public ArrayList<String> getCommitteeMemberNames() {
        return this.committeeMemberNames;
    }

    public ArrayList<Integer> getEnquiryIDs() {
        return this.enquiryIDs;
    }

    public ArrayList<Integer> getSuggestionIDs() {
        return this.suggestionIDs;
    }
}
