package cams.model;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 * A model object for all relevant camp information
 */
public class Camp implements Serializable {
    private int id;
    private String campName;

    /**
     * stores only the start and end date of the camp as
     * LocalDate objects, index-0: start, index-1: end
     */
    private ArrayList<LocalDate> dates;

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


    /**
     * Constructor for a new camp
     * @param campID The ID of the camp
     * @param campName The name of the camp
     * @param dates ArrayList<LocalDate> for the dates of the camp in the format <start, end>
     * @param faculty The faculty/user group of the camp
     * @param visibility Whether the camp is visible to all students
     * @param staffInCharge The name of the staff in charge
     * @param registrationClosingDate The registration close date
     */
    public Camp(int campID, String campName, ArrayList<LocalDate> dates, String faculty, boolean visibility, String staffInCharge, LocalDate registrationClosingDate) {
        // Default values
        this.id = campID;
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

    
    /**
     * Method for creating a defensive copy of the Camp object
     * @param other The original camp object to be copied
     */
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

    /**
     * Getter for campID
     * @return int
     */
    public int getId() {
        return id;
    }


    /**
     * Setter for campID
     * @param id The new camp ID
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Getter for camp name
     * @return String of camp name
     */
    public String getCampName() {
        return this.campName;
    }


    /**
     * Setter for camp name
     * @param campName The new camp name
     */
    public void setCampName(String campName) {
        this.campName = campName;
    }


    /**
     * Getter for camp start date
     * @return LocalDate object of camp start date
     */
    public LocalDate getStartDate() {
        return this.dates.get(0);
    }


    /**
     * Getter for camp end date
     * @return LocalDate object of camp end date
     */
    public LocalDate getEndDate() {
        return this.dates.get(1);
    }


    /**
     * Getter for the camp dates
     * @return ArrayList<LocalDate> of camp dates in format <start, end>
     */
    public ArrayList<LocalDate> getDates() {
        return this.dates;
    }


    /**
     * Setter for camp dates
     * @param dates ArrayList<LocalDate> of the new camp dates
     */
    public void setDates(ArrayList<LocalDate> dates) {
        this.dates = dates;
    }


    /**
     * Getter for camp registration close date
     * @return LocalDate object of the registration close date
     */
    public LocalDate getRegistrationClosingDate() {
        return this.registrationClosingDate;
    }


    /**
     * Setter for camp registration close date
     * @param registrationClosingDate LocalDate object for the registration close date
     */
    public void setRegistrationClosingDate(LocalDate registrationClosingDate) {
        this.registrationClosingDate = registrationClosingDate;
    }


    /**
     * Getter for camp visibility
     * @return boolean of whether camp is visible
     */
    public boolean getVisibility() {
        return this.visibility;
    }


    /**
     * Setter for camp visibility
     * @param visibility The new visibility for the camp
     */
    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }


    /**
     * Getter for the camp faculty/ user group
     * @return String of the faculty/ user group
     */
    public String getUserGroup() {
        return this.userGroup;
    }


    /**
     * Setter for camp faculty/ user group
     * @param userGroup The new faculty/ user group
     */
    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }


    /**
     * Getter for camp location
     * @return String of camp location
     */
    public String getLocation() {
        return this.location;
    }


    /**
     * Setter for camp location
     * @param location The new location for the camp
     */
    public void setLocation(String location) {
        this.location = location;
    }


    /**
     * Getter for max registerable slots for the camp
     * @return int of the max registetable slots
     */
    public int getTotalSlots() {
        return this.totalSlots;
    }


    /**
     * Setter for the max registerable slots for the camp
     * @param totalSlots The new max registerable slot
     */
    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }


    /**
     * Getter for the max committee slots
     * @return int of the max committee slots
     */
    public int getCommitteeSlots() {
        return this.committeeSlots;
    }


    /**
     * Setter for the max committee slots
     * @param committeeSlots The new max committee slots
     */
    public void setCommitteeSlots(int committeeSlots) {
        this.committeeSlots = committeeSlots;
    }


    /**
     * Getter for camp description
     * @return String of the camp description
     */
    public String getDescription() {
        return this.description;
    }


    /**
     * Setter for camp description
     * @param description The new camp description
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Getter for staff in charge name
     * @return String of the name of the  staff in charge
     */
    public String getStaffInCharge() {
        return this.staffInCharge;
    }


    /**
     * Setter for the staff in charge name of the camp.
     *
     * @param staffName The new name of the staff in charge.
     */
    public void setStaffInCharge(String staffName) {
        this.staffInCharge = staffName;
    } 


    /**
     * Getter for the names of the students registered
     * @return ArrayList<String> of student names
     */
    public ArrayList<String> getParticipatingStudentNames() {
        return this.participatingStudentNames;
    }


    /**
     * Getter for the names of the students who have withdrawn
     * @return ArrayList<String> of student names
     */
    public ArrayList<String> getWithdrawnStudentNames() {
        return this.withdrawnStudentNames;
    }


    /**
     * Getter for names of committee members
     * @return ArrayList<String> of the committee member names
     */
    public ArrayList<String> getCommitteeMemberNames() {
        return this.committeeMemberNames;
    }


    /**
     * Getter for enquiry IDs for the camp
     * @return ArrayList<Integer> of enquiry IDs
     */
    public ArrayList<Integer> getEnquiryIDs() {
        return this.enquiryIDs;
    }


    /**
     * Getter for suggestion IDs for the camp
     * @return ArrayList<Integer> of suggestion IDs
     */
    public ArrayList<Integer> getSuggestionIDs() {
        return this.suggestionIDs;
    }
}
