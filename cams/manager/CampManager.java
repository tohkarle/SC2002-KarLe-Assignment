package cams.manager;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;

import cams.model.Camp;
import cams.util.Serialize;
import cams.util.UniqueKey;

public class CampManager {

    private int uniqueKey = 0;
    private HashMap<Integer, Camp> campMap = new HashMap<Integer, Camp>();

    public CampManager(){
        Serialize.checkAndCreateFile("CampManagerKey.sav");
        Serialize.checkAndCreateFile("campMap.sav");
    }



    public void createCamp(String staffID) {
        // Set registration closing date to be 45 days after date of creation
        LocalDateTime registrationClosingDate = LocalDateTime.now().plus(45, ChronoUnit.DAYS);

        this.uniqueKey = UniqueKey.generateNewKey(this.uniqueKey);
        while(campMap.get(this.uniqueKey) != null) this.uniqueKey = UniqueKey.generateNewKey(this.uniqueKey);
        Camp newCamp = new Camp(this.uniqueKey, staffID, registrationClosingDate);
        campMap.put(this.uniqueKey, newCamp);
    }



    public ArrayList<Integer> getAllCampID(){
        ArrayList<Integer> arr = new ArrayList<>(campMap.keySet());
        return arr;
    }

    public ArrayList<String> getAllCampNames(String faculty) {
        ArrayList<String> names = new ArrayList<>();
        for (Camp camp : campMap.values()) {
            if (camp.getUserGroup() == faculty && camp.isVisible()) {
                names.add(camp.getCampName());
            }
        }
        return names;
    }


    public String getCampDescription(int campID){
        return campMap.get(campID).getDescription();
    }

    public void setCampDescription(int campID, String desc){
        campMap.get(campID).setDescription(desc);
    }



    public String getCampStaffInCharge(int campID){
        return (campMap.get(campID)).getStaffInCharge();
    }



    public String getCampLocation(int campID){
        return campMap.get(campID).getLocation();
    }

    public void setCampLocation(int campID, String location){
        campMap.get(campID).setLocation(location);
    }



    public String getCampName(int campID){
        return campMap.get(campID).getCampName();
    }

    public void setCampName(int campID, String name){
        campMap.get(campID).setCampName(name);
    }



    public Boolean getIsVisible(int campID){
        return campMap.get(campID).isVisible();
    }

    public void toggleIsVisible(int campID){
        campMap.get(campID).toggleIsVisible();
    }



    public String getUserGroup(int campID){
        return campMap.get(campID).getUserGroup();
    }

    public void setUserGroup(int campID, String userGroup){
        campMap.get(campID).setUserGroup(userGroup);
    }



    public ArrayList<Integer> getCampEnquiryIDs(int campID) {
        Camp camp = campMap.get(campID);
        return camp.getEnquiryIDs();
    }

    public void addEnquiryIDToCamp(int campID, int enquiryID) {
        Camp camp = campMap.get(campID);
        camp.addToEnquiryIDs(enquiryID);
    }

    public void removeEnquiryIDFromCamp(int campID, int enquiryID) {
        Camp camp = campMap.get(campID);
        camp.removeFromEnquiryIDs(enquiryID);
    }



    public ArrayList<Integer> getCampSuggestionIDs(int campID) {
        Camp camp = campMap.get(campID);
        return camp.getSuggestionIDs();
    }

    public void addSuggestionIDToCamp(int campID, int suggestionID) {
        Camp camp = campMap.get(campID);
        camp.addToSuggestionIDs(suggestionID);
    }

    public void removeSuggestionIDFromCamp(int campID, int suggestionID) {
        Camp camp = campMap.get(campID);
        camp.removeFromSuggestionIDs(suggestionID);
    }



    public ArrayList<String> getCampParticipatingStudentIDs(int campID) {
        Camp camp = campMap.get(campID);
        return camp.getParticipatingStudentIDs();
    }

    public void addStudentIDToCamp(int campID, String studentID) {
        Camp camp = campMap.get(campID);
        camp.addToParticipatingStudentIDs(studentID);
    }
    
    public void removeStudentIDFromCamp(int campID, String studentID) {
        Camp camp = campMap.get(campID);
        camp.removeFromParticipatingStudentIDs(studentID);
    }



    public int getRemainingSlots(int campID) {
        Camp camp = campMap.get(campID);
        return camp.getRemainingSlots();
    }

    public boolean isFull(int campID) {
        Camp camp = campMap.get(campID);
        return (camp.getRemainingSlots() == camp.getTotalSlots());
    }



    public int getRegCount(int campID){
        return campMap.get(campID).getRegCount();
    }

    public void addRegCount(int campID){
        campMap.get(campID).addRegCount();
    }

    public void minusRegCount(int campID){
        campMap.get(campID).minusRegCount();
    }



    public int getCommCount(int campID){
        return campMap.get(campID).getCommCount();
    }

    public void addCommCount(int campID){
        campMap.get(campID).addCommCount();
    }

    public void minusCommCount(int campID){
        campMap.get(campID).minusCommCount();
    }



    public int getMaxRegSlots(int campID){
        return campMap.get(campID).getMaxRegSlots();
    }

    public void setMaxRegSlots(int campID, int newMax){
        campMap.get(campID).setTotalSlots(newMax);
    }



    public int getMaxCommSlots(int campID){
        return campMap.get(campID).getMaxCommSlots();
    }

    public void setMaxCommSlots(int campID, int newMax){
        campMap.get(campID).setCommitteeSlots(newMax);
    }



    public boolean isAfterRegistrationClosingDate(int campID, LocalDateTime currentDate) {
        Camp camp = campMap.get(campID);
        return currentDate.isAfter(camp.getRegistrationClosingDate());
    }



    public ArrayList<LocalDateTime> getCampDates(int campID) {
        Camp camp = campMap.get(campID);
        return camp.getDates();
    }



    public LocalDateTime getStartDate(int campID){
        return getCampDates(campID).get(0);
    }

    public void setStartDate(int campID, LocalDateTime date){
        campMap.get(campID).setDate(0, date);
    }



    public LocalDateTime getEndDate(int campID){
        return getCampDates(campID).get(1);
    }

    public void setEndDate(int campID, LocalDateTime date){
        campMap.get(campID).setDate(1, date);
    }



    public LocalDateTime getRegCloseDate(int campID){
        return getCampDates(campID).get(2);
    }

    public void setRegCloseDate(int campID, LocalDateTime date){
        campMap.get(campID).setDate(2, date);
    }


    
    public void save(){
        Serialize.save("CampManagerKey.sav", uniqueKey);
        Serialize.save("campMap.sav", campMap);
    }

    public void load(){
        try{
            uniqueKey = (Integer)Serialize.load("CampManagerKey.sav");
            @SuppressWarnings("unchecked")
            HashMap<Integer, Camp> loadedMap = (HashMap<Integer, Camp>) Serialize.load("campMap.sav");
            campMap = loadedMap;
        }
        catch(Exception ex){
            uniqueKey = 0;
            campMap = new HashMap<Integer, Camp>();
        }
    }
}
