package cams.manager;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import cams.model.Camp;
import cams.model.RegistrationType;
import cams.service.CampService;

public class CampManager {

    private CampService campService;

    public CampManager(CampService campService) {
        this.campService = campService;
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
    }

    public void registerForCamp(String studentName, int campID, RegistrationType registrationType) {
        if (registrationType == RegistrationType.ATTENDEE) {
            campService.getParticipatingStudentNames(campID).add(studentName);
        } else if (registrationType == RegistrationType.COMMITTEE) {
            campService.getCommitteeMemberNames(campID).add(studentName);
        }
    }

    public void withdrawFromCamp(String studentName, int campID) {
        campService.getParticipatingStudentNames(campID).remove(studentName);
        campService.getWithdrawnStudentNames(campID).add(studentName);
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
