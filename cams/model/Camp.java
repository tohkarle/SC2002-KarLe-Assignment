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
    private int remainingSlots;
    private int registerCount;
    private int registerCommCount;
    private ArrayList<String> committeeMemberIDs;
    private ArrayList<String> participatingStudentIDs;
    private ArrayList<String> withdrawnStudentIDs;
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
        this.committeeSlots = 9;
        this.description = "Default description";
        this.registerCount = 0;
        this.registerCommCount = 0;

        this.id = id;
        this.staffInCharge = staffID;
        this.remainingSlots = totalSlots;
        this.registrationClosingDate = registrationClosingDate;

        this.committeeMemberIDs = new ArrayList<String>();
        this.participatingStudentIDs = new ArrayList<String>();
        this.withdrawnStudentIDs = new ArrayList<String>();
        this.enquiryIDs = new ArrayList<Integer>();
        this.suggestionIDs = new ArrayList<Integer>();
    }

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

    public ArrayList<LocalDate> getDates() {
        return this.dates;
    }

    public void setDates(ArrayList<LocalDate> dates) {
        this.dates = dates;
    }

    public void setDate(int index, LocalDate date){
        this.dates.set(index, date);
    }

    public LocalDate getRegistrationClosingDate() {
        return this.registrationClosingDate;
    }

    public void setRegistrationClosingDate(LocalDate registrationClosingDate) {
        this.registrationClosingDate = registrationClosingDate;
    }

    public boolean isVisible() {
        return this.visibility;
    }

    public void toggleIsVisible(){
        this.visibility = !this.visibility;
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


    public void addRegCount(){
        registerCount++;
    }

    public void minusRegCount(){
        registerCount--;
    }

    public void addCommCount(){
        registerCommCount++;
    }

    public void minusCommCount(){
        registerCommCount--;
    }

    public int getRegCount(){
        return registerCount;
    }

    public int getCommCount(){
        return registerCommCount;
    }

    public int getMaxRegSlots(){
        return totalSlots;
    }

    public int getMaxCommSlots(){
        return committeeSlots;
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

    public int getRemainingSlots() {
        return this.remainingSlots;
    }

    public void setRemainingSlots(int remainingSlots) {
        this.remainingSlots = remainingSlots;
    }

    public ArrayList<String> getParticipatingStudentIDs() {
        return this.participatingStudentIDs;
    }

    public void addToParticipatingStudentIDs(String studentID) {
        this.participatingStudentIDs.add(studentID);
    }

    public void removeFromParticipatingStudentIDs(String studentID) {
        this.participatingStudentIDs.remove(studentID);
    }

    public ArrayList<String> getWithdrawnStudentIDs() {
        return this.withdrawnStudentIDs;
    }

    public void addToWithdrawnStudentIDs(String studentID) {
        this.withdrawnStudentIDs.add(studentID);
    }

    public void removeFromWithdrawnStudentIDs(String studentID) {
        this.withdrawnStudentIDs.remove(studentID);
    }

    public ArrayList<String> getCommitteeMemberIDs() {
        return this.committeeMemberIDs;
    }

    public void addToCommitteeMemberIDs(String studentID) {
        this.committeeMemberIDs.add(studentID);
    }

    public void removeFromCommitteeMemberIDs(String studentID) {
        this.committeeMemberIDs.remove(studentID);
    }

    public ArrayList<Integer> getEnquiryIDs() {
        return this.enquiryIDs;
    }

    public void addToEnquiryIDs(int enquiryID) {
        this.enquiryIDs.add(enquiryID);
    }

    public void removeFromEnquiryIDs(int enquiryID) {
        this.enquiryIDs.remove(enquiryID);
    }

    public ArrayList<Integer> getSuggestionIDs() {
        return this.suggestionIDs;
    }

    public void addToSuggestionIDs(int suggestionID) {
        this.enquiryIDs.add(suggestionID);
    }

    public void removeFromSuggestionIDs(int suggestionID) {
        this.suggestionIDs.remove(suggestionID);
    }
}
