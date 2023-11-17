package cams.manager;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import cams.model.Camp;
import cams.model.RegistrationType;
import cams.service.CampService;

public class CampManager {

    private CampService campService;
    private int viewCampDetail;

    public CampManager(CampService campService) {
        this.campService = campService;
    }

    public int getViewCampDetail() {
        return this.viewCampDetail;
    }

    public void setViewCampDetail(int option) {
        this.viewCampDetail = option;
    }

    public Camp getCampInfo() {
        return campService.getCamp(viewCampDetail);
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
        Camp newCamp = new Camp(campName, dates, faculty, visibility, staffName, registrationClosingDate);
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
}
