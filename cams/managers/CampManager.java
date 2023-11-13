package cams.managers;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cams.models.Camp;
import cams.models.RegistrationType;
import cams.utils.Serialize;
import cams.utils.UniqueKey;

public class CampManager {

    private int uniqueKey = 0;
    private HashMap<Integer, Camp> campMap = new HashMap<Integer, Camp>();

    public CampManager(){
        Serialize.checkAndCreateFile("CampManagerKey.sav");
        Serialize.checkAndCreateFile("campMap.sav");
    }

    public int createCamp(int staffID, String campName, ArrayList<LocalDate> dates, String faculty, boolean visibility) {
        // Set registration closing date to be 45 days after date of creation
        LocalDate registrationClosingDate = LocalDate.now().plus(45, ChronoUnit.DAYS);

        this.uniqueKey = UniqueKey.generateNewKey(this.uniqueKey);
        while(campMap.get(this.uniqueKey) != null) this.uniqueKey = UniqueKey.generateNewKey(this.uniqueKey);
        Camp newCamp = new Camp(this.uniqueKey, campName, dates, faculty, visibility, staffID, registrationClosingDate);
        campMap.put(this.uniqueKey, newCamp);
        return this.uniqueKey;
    }

    public void deleteCamp(int campID) {
        campMap.remove(campID);
    }



    public ArrayList<Integer> getAllCampIDs(){
        ArrayList<Integer> arr = new ArrayList<>(campMap.keySet());
        return arr;
    }

    public ArrayList<String> getAllCampNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Camp camp : campMap.values()) {
            names.add(camp.getCampName());
        }
        return names;
    }

    public ArrayList<String> getAllStaffCampNames(int staffID) {
        ArrayList<String> names = new ArrayList<>();
        for (Camp camp : campMap.values()) {
            if (camp.getStaffInCharge() == staffID) {
                names.add(camp.getCampName());
            }
        }
        return names;
    }

    public ArrayList<Integer> getAllStaffCampIDs(int staffID) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Camp camp : campMap.values()) {
            if (camp.getStaffInCharge() == staffID) {
                ids.add(camp.getId());
            }
        }
        return ids;
    }

    public ArrayList<String> getAllFacultyCampNames(String faculty) {
        ArrayList<String> names = new ArrayList<>();
        for (Camp camp : campMap.values()) {
            if ((camp.getUserGroup().equals(faculty) || camp.getUserGroup().equals("NTU")) && camp.isVisible()) {
                names.add(camp.getCampName());
            }
        }
        return names;
    }

    public ArrayList<Integer> getAllFacultyCampIDs(String faculty) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Camp camp : campMap.values()) {
            if ((camp.getUserGroup().equals(faculty) || camp.getUserGroup().equals("NTU")) && camp.isVisible()) {
                ids.add(camp.getId());
            }
        }
        return ids;
    }

    public ArrayList<String> getAllRegisteredCampNames(int studentID) {
        ArrayList<String> names = new ArrayList<String>();
        for (Camp camp : campMap.values()) {
            if ((camp.getParticipatingStudentIDs().contains(studentID) || camp.getCommitteeMemberIDs().contains(studentID)) && camp.isVisible()) {
                names.add(camp.getCampName());
            }
        }
        return names;
    }

    public ArrayList<Integer> getAllRegisteredCampIDs(int studentID) {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (Camp camp : campMap.values()) {
            if ((camp.getParticipatingStudentIDs().contains(studentID) || camp.getCommitteeMemberIDs().contains(studentID)) && camp.isVisible()) {
                ids.add(camp.getId());
            }
        }
        return ids;
    }

    


    public String getCampDescription(int campID){
        return campMap.get(campID).getDescription();
    }

    public void setCampDescription(int campID, String desc){
        campMap.get(campID).setDescription(desc);
    }



    // public String getCampStaffInCharge(int campID){
    //     return (campMap.get(campID)).getStaffInCharge();
    // }



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



    public Boolean getVisibility(int campID){
        return campMap.get(campID).isVisible();
    }

    public void setVisibility(int campID, boolean visibility) {
        campMap.get(campID).setVisibility(visibility);
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



    public ArrayList<Integer> getCampParticipatingStudentIDs(int campID) {
        Camp camp = campMap.get(campID);
        return camp.getParticipatingStudentIDs();
    }

    public void addStudentIDToCamp(int campID, int studentID) {
        Camp camp = campMap.get(campID);
        camp.addToParticipatingStudentIDs(studentID);
    }
    
    public void removeStudentIDFromCamp(int campID, int studentID) {
        campMap.get(campID).removeFromParticipatingStudentIDs(studentID);
    }



    public int getRemainingSlots(int campID) {
        Camp camp = campMap.get(campID);
        return (camp.getTotalSlots() - camp.getParticipatingStudentIDs().size());
    }

    public int getTotalSlots(int campID) {
        return campMap.get(campID).getTotalSlots();
    }

    public void setTotalSlots(int campID, int totalSlots) {
        campMap.get(campID).setTotalSlots(totalSlots);
    }

    public int getRemainingCommitteeSlots(int campID) {
        Camp camp = campMap.get(campID);
        return (camp.getCommitteeSlots() - camp.getCommitteeMemberIDs().size());
    }

    public int getTotalCommitteeSlots(int campID) {
        return campMap.get(campID).getCommitteeSlots();
    }



    public boolean participationIsFull(int campID) {
        return (this.getRemainingSlots(campID) == 0);
    }

    public boolean committeeIsFull(int campID) {
        Camp camp = campMap.get(campID);
        return (camp.getCommitteeSlots() == camp.getCommitteeMemberIDs().size());
    }



    public int getMaxCommSlots(int campID){
        return campMap.get(campID).getMaxCommSlots();
    }

    public void setMaxCommSlots(int campID, int newMax){
        campMap.get(campID).setCommitteeSlots(newMax);
    }



    public boolean isAfterRegistrationClosingDate(int campID, LocalDate currentDate) {
        Camp camp = campMap.get(campID);
        return currentDate.isAfter(camp.getRegistrationClosingDate());
    }



    public ArrayList<LocalDate> getCampDates(int campID) {
        return campMap.get(campID).getDates();
    }

    public void setCampDates(int campID, ArrayList<LocalDate> dates) {
        campMap.get(campID).setDates(dates);
    }



    public LocalDate getStartDate(int campID){
        return getCampDates(campID).get(0);
    }

    public void setStartDate(int campID, LocalDate date){
        campMap.get(campID).setDate(0, date);
    }



    public LocalDate getEndDate(int campID){
        return getCampDates(campID).get(1);
    }

    public void setEndDate(int campID, LocalDate date){
        campMap.get(campID).setDate(1, date);
    }




    public LocalDate getRegCloseDate(int campID){
        return getCampDates(campID).get(2);
    }

    public void setRegCloseDate(int campID, LocalDate date){
        campMap.get(campID).setDate(2, date);
    }




    public void registerForCamp(int studentID, int campID, RegistrationType registrationType) {

        Camp camp = campMap.get(campID);

        // If student registers as ATTENDEE, add student to participating student list
        // Else add to committee member list
        if (registrationType == RegistrationType.ATTENDEE) {
            camp.addToParticipatingStudentIDs(studentID);
        } else {
            camp.addToCommitteeMemberIDs(studentID);
        }
    }

    public void withdrawFromCamp(int studentID, int campID) {
        Camp camp = campMap.get(campID);
        camp.removeFromParticipatingStudentIDs(studentID);
        camp.addToWithdrawnStudentIDs(studentID);
    }


    public boolean hasRegisteredForCamp(int studentID, int campID) {
        Camp camp = campMap.get(campID);
        if (camp.getParticipatingStudentIDs().contains(studentID) || camp.getCommitteeMemberIDs().contains(studentID)) { return true; }
        return false;
    }

    public boolean hasCampClashes(int studentID, int campID) {
        Camp newCamp = campMap.get(campID);
        LocalDate newCampStart = newCamp.getStartDate();
        LocalDate newCampEnd = newCamp.getEndDate();
    
        for (Camp camp : campMap.values()) {
            if (camp.getParticipatingStudentIDs().contains(studentID)) {
                if ((newCampStart.isAfter(camp.getStartDate()) && newCampStart.isBefore(camp.getEndDate())) ||
                    (newCampEnd.isAfter(camp.getStartDate()) && newCampEnd.isBefore(camp.getEndDate())) ||
                    (newCampStart.isEqual(camp.getStartDate()) || newCampEnd.isEqual(camp.getEndDate()))) {
                    return true;
                }
            }

            if (camp.getCommitteeMemberIDs().contains(studentID)) {
                if ((newCampStart.isAfter(camp.getStartDate()) && newCampStart.isBefore(camp.getEndDate())) ||
                    (newCampEnd.isAfter(camp.getStartDate()) && newCampEnd.isBefore(camp.getEndDate())) ||
                    (newCampStart.isEqual(camp.getStartDate()) || newCampEnd.isEqual(camp.getEndDate()))) {
                    return true;
                }
            }
        }
        return false;
    }

    public String committeeMemberFor(int studentID) {
        for (Camp camp : campMap.values()) {
            if (camp.getCommitteeMemberIDs().contains(studentID)) {
                return camp.getCampName();
            }
        }
        return null;
    }

    public boolean isACommitteeMember(int studentID) {
        for (Camp camp : campMap.values()) {
            if (camp.getCommitteeMemberIDs().contains(studentID)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCommitteeMemberForAnotherCamp(int studentID, int campID) {
        for (Camp camp : campMap.values()) {
            if (camp.getCommitteeMemberIDs().contains(studentID) && camp.getId() != campID) {
                return true;
            }
        }
        return false;
    }

    public int getCampStaffInCharge(int campID) {
        return campMap.get(campID).getStaffInCharge();
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

    public int getRegCount(int campID) {
        return campMap.get(campID).getParticipatingStudentIDs().size();
    }

    public void kickLastJoin(int campID) {
        List<Integer> participatingStudentIDs = campMap.get(campID).getParticipatingStudentIDs();

        // Check if the list is not empty before attempting to remove the last element
        if (!participatingStudentIDs.isEmpty()) {
            int lastIndex = participatingStudentIDs.size() - 1;
            participatingStudentIDs.remove(lastIndex);
        } else {
            // Handle the case where the list is empty, if needed
            System.out.println("There is no student in this camp.");
        }
    }

    public void kickLastCommittee(int campID) {
        List<Integer> committeeMemberIDs = campMap.get(campID).getCommitteeMemberIDs();

        // Check if the list is not empty before attempting to remove the last element
        if (!committeeMemberIDs.isEmpty()) {
            int lastIndex = committeeMemberIDs.size() - 1;
            committeeMemberIDs.remove(lastIndex);
        } else {
            // Handle the case where the list is empty, if needed
            System.out.println("There is no committee in this camp.");
        }
    }

    public LocalDate getRegistrationClosingDate(int campID) {
        return campMap.get(campID).getRegistrationClosingDate();
    }

}
