package cams.manager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import cams.model.Camp;
import cams.model.RegistrationType;
import cams.service.CampService;
import cams.utils.LoadingIndicator;
import cams.utils.UniqueKey;


/**
 * A high level controller for the camp objects,
 * is a singleton object
 */
public class CampManager {

    /**
     * A singleton reference to this object
     */
    private static CampManager instance;

    /**
     * A singleton reference to the camp service object
     */
    private CampService campService;


    /**
     * Initialize this object
     */
    public CampManager() {
        this.campService = new CampService();
    }


    /**
     * A public static method for other objects to get this singleton object
     * @return CampManager object, this object
     */
    public static CampManager getInstance() {
        if (instance == null) {
            instance = new CampManager();
        }
        return instance;
    }


    /**
     * A method to get a camp object via campID
     * @param campID The ID of the camp
     * @return Camp object (copy)
     */
    public Camp getCamp(int campID) {
        return campService.getCamp(campID);
    }

    // KARLE_TODO: Also remember to change the (New) implementation
    public ArrayList<Camp> getAllCampsByNameSorted() {
        return campService.getAllCampsByNameSorted();
    }
    
    public ArrayList<Camp> getStaffCampsByNameSorted(String staffName) {
        return campService.getStaffCampsByNameSorted(staffName);
    }
    
    public ArrayList<Camp> getFacultyCampsByNameSorted(String faculty) {
        return campService.getFacultyCampsByNameSorted(faculty);
    }

    public ArrayList<Camp> getRegisteredCampsByNameSorted(String studentName) {
        return campService.getRegisteredCampsByNameSorted(studentName);
    }

    public ArrayList<String> getParticipatingStudentNames(int campID) {
        return campService.getParticipatingStudentNames(campID);
    }

    public ArrayList<String> getCommitteeMemberNames(int campID) {
        return campService.getCommitteeMemberNames(campID);
    }


    /**
     * A method to create a new camp
     * @param staffName The name of the staff that created the camp
     * @param campName The name of the camp
     * @param dates The start and end date for the camp
     * @param faculty The faculty the camp is for
     * @param visibility The current visibility of the camp to students
     * @return boolean of whether the camp was successfully created
     */
    public boolean createCampSuccessful(String staffName, String campName, ArrayList<LocalDate> dates, String faculty, boolean visibility) {
        if (campService.getCampIDWithName(campName) != -1) {
            LoadingIndicator.customLoadingIndicator("Creating camp...", "Camp of the same name already exists, please choose another name.");
            return false;
        }

        // Set registration closing date to be 45 days after date of creation
        LocalDate registrationClosingDate = dates.get(0).minusDays(1);

        // Create new camp and add to 'database'
        int uniqueKey = 0;
        uniqueKey = UniqueKey.generateNewKey(uniqueKey);
        while(campService.getCampMap().get(uniqueKey) != null) uniqueKey = UniqueKey.generateNewKey(uniqueKey);
        Camp newCamp = new Camp(uniqueKey, campName, dates, faculty, visibility, staffName, registrationClosingDate);
        campService.createCamp(newCamp);
        campService.save();
        return true;
    }


    /**
     * A method to delete a camp via campID
     * @param campID The ID of the camp
     */
    public void deleteCamp(int campID) {
        campService.deleteCamp(campID);
        campService.save();
    }


    /**
     * A method to register a user for the camp
     * @param studentName The name of the student
     * @param campID The ID of the camp
     * @param registrationType The registration type
     */
    public void registerForCamp(String studentName, int campID, RegistrationType registrationType) {
        if (registrationType == RegistrationType.ATTENDEE) {
            campService.getParticipatingStudentNames(campID).add(studentName);
        } else if (registrationType == RegistrationType.COMMITTEE) {
            campService.getCommitteeMemberNames(campID).add(studentName);
        }
        campService.save();
    }


    /**
     * A method to withdraw a user for the camp
     * @param studentName The name of the student
     * @param campID The ID of the camp
     */
    public void withdrawFromCamp(String studentName, int campID) {
        campService.getParticipatingStudentNames(campID).remove(studentName);
        campService.getWithdrawnStudentNames(campID).add(studentName);
        campService.save();
    }


    /**
     * A method to update the camp object in memory
     * @param camp The new camp
     * @return boolean of whether the update was successful
     */
    public boolean updateCampSuccessful(Camp camp) {
        int campID = campService.getCampIDWithName(camp.getCampName());
        if (campID != -1 && campID != camp.getId()) {
            LoadingIndicator.customLoadingIndicator("Editing camp...", "Camp of the same name already exists, please choose another name.");
            return false;
        }
        campService.updateCamp(camp);
        campService.save();
        return true;
    }


    /**
     * A method to check if a user has already registered for the camp
     * @param studentName The name of the student
     * @param campID The ID of the camp
     * @return boolean of whether user has registered for the camp
     */
    public boolean hasRegisteredForCamp(String studentName, int campID) {
        if (campService.getParticipatingStudentNames(campID).contains(studentName) || campService.getCommitteeMemberNames(campID).contains(studentName)) { return true; }
        return false;
    }


    /**
     * A method to check if the user has withdrawn before from the camp
     * @param studentName The name of the user
     * @param campID The ID of the camp
     * @return boolean of whether user has withdrawn from the camp before
     */
    public boolean hasWithdrawnFromCamp(String studentName, int campID) {
        if (campService.getWithdrawnStudentNames(campID).contains(studentName)) { return true; }
        return false;
    }


    /**
     * A method to check if the user has clashes if specified camp is registered for will
     * have clashes with the other registered camps
     * @param studentName The name of the student
     * @param campID The ID of the camp
     * @return boolean of whether the specified camp will clash with other camps the user has registered for
     */
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


    /**
     * A method to get the camp name the user is a committee member for
     * @param studentName The name of the student
     * @return String of the camp name the user is a committee for
     */
    public String committeeMemberFor(String studentName) {
        for (Camp camp : campService.getCampMap().values()) {
            if (camp.getCommitteeMemberNames().contains(studentName)) {
                return camp.getCampName();
            }
        }
        return null;
    }


    /**
     * A method to check if the user has registered as committee for a camp
     * @param studentName The name of the student
     * @return boolean of whether the user has registered as committee for a camp
     */
    public boolean isACommitteeMember(String studentName) {
        for (Camp camp : campService.getCampMap().values()) {
            if (camp.getCommitteeMemberNames().contains(studentName)) {
                return true;
            }
        }
        return false;
    }


    /**
     * A method to check if user is a committee for specified camp
     * @param studentName The name of the user
     * @param campID The ID of the camp
     * @return boolean of whether user is a committee for the specified camp
     */
    public boolean isACommitteeMemberOfThisCamp(String studentName, int campID) {
        if (campService.getCommitteeMemberNames(campID).contains(studentName)) { return true; }
        return false;
    }


    /**
     * A method to check if user is a committee for a camp other than the specified camp
     * @param studentName The name of the user
     * @param campID The ID of the camp
     * @return boolean of whether user is a committee for a camp other than the specified camp
     */
    public boolean isCommitteeMemberForAnotherCamp(String studentName, int campID) {
        for (Camp camp : campService.getCampMap().values()) {
            if (camp.getCommitteeMemberNames().contains(studentName) && camp.getId() != campID) {
                return true;
            }
        }
        return false;
    }


    /**
     * A method to check if the registration close date has passed for a specified camp
     * @param campID The ID of the camp
     * @param currentDate The current date as a LocalDate object
     * @return boolean of whether the current date is after registration close date for specified camp
     */
    public boolean isAfterRegistrationClosingDate(int campID, LocalDate currentDate) {
        return currentDate.isAfter(campService.getCamp(campID).getRegistrationClosingDate());
    }


    /**
     * A method to check if the specified camp is full
     * @param campID The ID of the camp
     * @return boolean of whether the camp is full
     */
    public boolean participationIsFull(int campID) {
        return (campService.getCamp(campID).getTotalSlots() == (campService.getCamp(campID).getParticipatingStudentNames().size() + campService.getCamp(campID).getCommitteeMemberNames().size()));
    }


    /**
     * A method to check if the specified camp has all committee slots filled
     * @param campID The ID of the camp
     * @return boolean of whether the camp has all committee slots filled
     */
    public boolean committeeIsFull(int campID) {
        return (campService.getCamp(campID).getCommitteeSlots() == campService.getCamp(campID).getCommitteeMemberNames().size());
    }


    /**
     * A method to demote the last committee member to join to a attendee
     * @param campID The ID of the camp
     */
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


    /**
     * A method to kick the last attendee that joined the specified camp
     * @param campID The ID of the camp
     */
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


    /**
     * A method to get the number of attendee and committee registered
     * @param campID The ID of the camp
     * @return int of the number of registered for the camp
     */
    public int getRegCount(int campID) {
        return campService.getParticipatingStudentNames(campID).size();
    }


    /**
     * A method to get the name of the specified camp
     * @param campID The ID of the camp
     * @return String of the camp name
     */
    public String getCampName(int campID) {
        return campService.getCampName(campID);
    }
}
