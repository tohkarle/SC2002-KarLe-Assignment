package cams.manager;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import cams.model.Camp;
import cams.model.RegistrationType;
import cams.service.CampService;
import cams.utils.UniqueKey;

public class CampManager {

    private CampService campService;
    private int selectedCampID;
    private int selectedCampInfo;

    private Camp tempCamp;

    public CampManager(CampService campService) {
        this.campService = campService;
        this.selectedCampID = -1;
    }

    public void createTempCamp() {
        this.tempCamp = campService.getCamp(selectedCampID);
    }

    public Camp getTempCamp() {
        return this.tempCamp;
    }

    public void clearTempCamp() {
        this.tempCamp = null;
    }

    public int getSelectedID() {
        return this.selectedCampID;
    }

    public void setSelectedCampID(int option) {
        this.selectedCampID = option;
    }

    public int getSelectedCampInfo() {
        return this.selectedCampInfo;
    }

    public void setSelectedCampInfo(int option) {
        this.selectedCampInfo = option;
    }

    public Camp getSelectedCamp() {
        System.out.println("GET FROM DATABASE");
        return campService.getCamp(selectedCampID);
    }

    public ArrayList<Camp> getAllCamps() {
        return campService.getAllCamps();
    }
    
    public ArrayList<Camp> getStaffCamps(String staffName) {
        return campService.getStaffCamps(staffName);
    }
    
    public ArrayList<Camp> getFacultyCamps(String faculty) {
        return campService.getFacultyCamps(faculty);
    }

    public ArrayList<Integer> getAllCampIDs(){
        ArrayList<Integer> arr = new ArrayList<>(campService.getCampMap().keySet());
        return arr;
    }

    public ArrayList<String> getAllCampNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Camp camp : campService.getCampMap().values()) {
            names.add(camp.getCampName());
        }
        return names;
    }

    public ArrayList<String> getAllStaffCampNames(String staffName) {
        ArrayList<String> names = new ArrayList<>();
        for (Camp camp : campService.getCampMap().values()) {
            if (camp.getStaffInCharge().equals(staffName)) {
                names.add(camp.getCampName());
            }
        }
        return names;
    }

    public ArrayList<Integer> getAllStaffCampIDs(String staffName) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Camp camp : campService.getCampMap().values()) {
            if (camp.getStaffInCharge().equals(staffName)) {
                ids.add(camp.getId());
            }
        }
        return ids;
    }

    public ArrayList<String> getAllFacultyCampNames(String faculty) {
        ArrayList<String> names = new ArrayList<>();
        for (Camp camp : campService.getCampMap().values()) {
            if ((camp.getUserGroup().equals(faculty) || camp.getUserGroup().equals("NTU")) && camp.getVisibility()) {
                names.add(camp.getCampName());
            }
        }
        return names;
    }

    public ArrayList<Integer> getAllFacultyCampIDs(String faculty) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Camp camp : campService.getCampMap().values()) {
            if ((camp.getUserGroup().equals(faculty) || camp.getUserGroup().equals("NTU")) && camp.getVisibility()) {
                ids.add(camp.getId());
            }
        }
        return ids;
    }

    public ArrayList<String> getAllRegisteredCampNames(String studentName) {
        ArrayList<String> names = new ArrayList<String>();
        for (Camp camp : campService.getCampMap().values()) {
            if ((camp.getParticipatingStudentNames().contains(studentName) || camp.getCommitteeMemberNames().contains(studentName)) && camp.getVisibility()) {
                names.add(camp.getCampName());
            }
        }
        return names;
    }

    public ArrayList<Integer> getAllRegisteredCampIDs(String studentName) {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (Camp camp : campService.getCampMap().values()) {
            if ((camp.getParticipatingStudentNames().contains(studentName) || camp.getCommitteeMemberNames().contains(studentName)) && camp.getVisibility()) {
                ids.add(camp.getId());
            }
        }
        return ids;
    }


    public boolean createCampSuccessful(String staffName, String campName, ArrayList<LocalDate> dates, String faculty, boolean visibility) {
        if (campService.campAlreadyExists(campName)) {
            System.out.println("Camp of the same name already exists, please choose another name.");
            return false;
        }

        // Set registration closing date to be 45 days after date of creation
        LocalDate registrationClosingDate = LocalDate.now().plus(45, ChronoUnit.DAYS);

        // Create new camp and add to 'database'
        int uniqueKey = 0;
        uniqueKey = UniqueKey.generateNewKey(uniqueKey);
        while(campService.getCampMap().get(uniqueKey) != null) uniqueKey = UniqueKey.generateNewKey(uniqueKey);
        Camp newCamp = new Camp(uniqueKey, campName, dates, faculty, visibility, staffName, registrationClosingDate);
        System.out.println("New camp: " + newCamp.getId());
        campService.createCamp(newCamp);
        campService.save();
        return true;
    }

    public void deleteCamp(int campID) {
        campService.deleteCamp(campID);
        campService.save();
    }

    public void registerForCamp(String studentName, int campID, RegistrationType registrationType) {
        if (registrationType == RegistrationType.ATTENDEE) {
            campService.getParticipatingStudentNames(campID).add(studentName);
        } else if (registrationType == RegistrationType.COMMITTEE) {
            campService.getCommitteeMemberNames(campID).add(studentName);
        }
        campService.save();
    }

    public void withdrawFromCamp(String studentName, int campID) {
        campService.getParticipatingStudentNames(campID).remove(studentName);
        campService.getWithdrawnStudentNames(campID).add(studentName);
        campService.save();
    }

    public void updateCamp(Camp camp) {
        campService.updateCamp(camp);
        campService.save();
    }

    public boolean hasRegisteredForCamp(String studentName, int campID) {
        if (campService.getParticipatingStudentNames(campID).contains(studentName) || campService.getCommitteeMemberNames(campID).contains(studentName)) { return true; }
        return false;
    }

    public boolean hasWithdrawnFromCamp(String studentName, int campID) {
        if (campService.getWithdrawnStudentNames(campID).contains(studentName)) { return true; }
        return false;
    }

    public boolean hasCampClashes(String studentName, int campID) {
        Camp newCamp = campService.getCamp(campID);
        LocalDate newCampStart = newCamp.getStartDate();
        LocalDate newCampEnd = newCamp.getEndDate();
    
        for (Camp camp : campService.getCampMap().values()) {
            if (camp.getParticipatingStudentNames().contains(studentName)) {
                if ((newCampStart.isAfter(camp.getStartDate()) && newCampStart.isBefore(camp.getEndDate())) ||
                    (newCampEnd.isAfter(camp.getStartDate()) && newCampEnd.isBefore(camp.getEndDate())) ||
                    (newCampStart.isEqual(camp.getStartDate()) || newCampEnd.isEqual(camp.getEndDate()))) {
                    return true;
                }
            }

            if (camp.getCommitteeMemberNames().contains(studentName)) {
                if ((newCampStart.isAfter(camp.getStartDate()) && newCampStart.isBefore(camp.getEndDate())) ||
                    (newCampEnd.isAfter(camp.getStartDate()) && newCampEnd.isBefore(camp.getEndDate())) ||
                    (newCampStart.isEqual(camp.getStartDate()) || newCampEnd.isEqual(camp.getEndDate()))) {
                    return true;
                }
            }
        }
        return false;
    }

    public String committeeMemberFor(String studentName) {
        for (Camp camp : campService.getCampMap().values()) {
            if (camp.getCommitteeMemberNames().contains(studentName)) {
                return camp.getCampName();
            }
        }
        return null;
    }

    public boolean isACommitteeMember(String studentName) {
        for (Camp camp : campService.getCampMap().values()) {
            if (camp.getCommitteeMemberNames().contains(studentName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isACommitteeMemberOfThisCamp(String studentName, int campID) {
        if (campService.getCommitteeMemberNames(campID).contains(studentName)) { return true; }
        return false;
    }

    public boolean isCommitteeMemberForAnotherCamp(String studentName, int campID) {
        for (Camp camp : campService.getCampMap().values()) {
            if (camp.getCommitteeMemberNames().contains(studentName) && camp.getId() != campID) {
                return true;
            }
        }
        return false;
    }

    public boolean isAfterRegistrationClosingDate(int campID, LocalDate currentDate) {
        return currentDate.isAfter(campService.getCamp(campID).getRegistrationClosingDate());
    }

    public boolean participationIsFull(int campID) {
        return (campService.getCamp(campID).getTotalSlots() == campService.getCamp(campID).getParticipatingStudentNames().size());
    }

    public boolean committeeIsFull(int campID) {
        return (campService.getCamp(campID).getCommitteeSlots() == campService.getCamp(campID).getCommitteeMemberNames().size());
    }

    public void demoteLastJoin(int campID) {
        List<String> commiteeMemberNames = campService.getCommitteeMemberNames(campID);

        // Check if the list is not empty before attempting to remove the last element
        if (!commiteeMemberNames.isEmpty()) {
            int lastIndex = commiteeMemberNames.size() - 1;
            commiteeMemberNames.remove(lastIndex);
        } else {
            // Handle the case where the list is empty, if needed
            System.out.println("There is no committee member in this camp.");
        }
    }

    public void kickLastJoin(int campID) {
        List<String> participatingStudentNames = campService.getParticipatingStudentNames(campID);

        // Check if the list is not empty before attempting to remove the last element
        if (!participatingStudentNames.isEmpty()) {
            int lastIndex = participatingStudentNames.size() - 1;
            participatingStudentNames.remove(lastIndex);
        } else {
            // Handle the case where the list is empty, if needed
            System.out.println("There is no student in this camp.");
        }
    }

    public int getRegCount(int campID) {
        return campService.getParticipatingStudentNames(campID).size();
    }
}
