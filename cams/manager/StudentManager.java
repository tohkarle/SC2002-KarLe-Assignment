package cams.manager;

import java.time.LocalDateTime;
import java.util.ArrayList;

import cams.Main;
import cams.model.Student;

public class StudentManager extends UserManager {
    // Student-specific methods
    // KARLE_TODO: Implement
    // student.addToCampCommittee(campID)
    // student.isCampCommitteeForCamp(campID)
    // student.getRegistrationIDs()
    // student.getEnquiryIDs()
    // student.getSuggestionIDs()
    // student.addToRegistrationIDs(registrationID)
    // student.addToEnquiryIDs(enquiryID)
    // student.addToSuggestionIDs(suggestionID)
    // student.removeFromEnquiryIDs(enquiryID)
    // student.removeFromSuggestionIDs(suggestionID)
    // student.addOnePoint()

    public void addCampCommitteeToStudent(String studentID, int campID) {
        Student student = (Student) userMap.get(studentID);
        student.addToCampCommittee(campID);
    }

    public boolean isCampCommitteeForCamp(String studentID, int campID) {
        Student student = (Student) userMap.get(studentID);
        return (student.isCampCommitteeForCamp(campID));
    }

    public ArrayList<Integer> getStudentRegistrationIDs(String studentID) {
        Student student = (Student) userMap.get(studentID);
        return student.getRegistrationIDs();
    }

    public ArrayList<Integer> getStudentEnquiryIDs(String studentID) {
        Student student = (Student) userMap.get(studentID);
        return student.getEnquiryIDs();
    }

    public ArrayList<Integer> getStudentSuggestionIDs(String studentID) {
        Student student = (Student) userMap.get(studentID);
        return student.getSuggestionIDs();
    }

    public void addRegistrationIDToStudent(String studentID, int registrationID) {
        Student student = (Student) userMap.get(studentID);
        student.addToRegistrationIDs(registrationID);
    }

    public void addEnquiryIDToStudent(String studentID, int enquiryID) {
        Student student = (Student) userMap.get(studentID);
        student.addToEnquiryIDs(enquiryID);
    }

    public void addSuggestionIDToStudent(String studentID, int suggestionID) {
        Student student = (Student) userMap.get(studentID);
        student.addToSuggestionIDs(suggestionID);
    }

    public void removeEnquiryIDFromStudent(String studentID, int enquiryID) {
        Student student = (Student) userMap.get(studentID);
        student.removeFromEnquiryIDs(enquiryID);
    }

    public void removeSuggestionIDFromStudent(String studentID, int suggestionID) {
        Student student = (Student) userMap.get(studentID);
        student.removeFromSuggestionIDs(suggestionID);
    }

    public boolean hasRegisteredForCamp(String studentID, int campID) {
        Student student = (Student) userMap.get(studentID);
        for (int registrationID : student.getRegistrationIDs()) {
            if (Main.registrationManager.getCampID(registrationID) == campID) { return true; }
        }
        return false;
    }

    public boolean hasCampClashes(String studentID, int campID) {
        Student student = (Student) userMap.get(studentID);
        ArrayList<LocalDateTime> newCampDates = Main.campManager.getCampDates(campID);

        // KARLE_TODO: Think of better implementation
        for (int registrationID : student.getRegistrationIDs()) {
            int tempCampID = Main.registrationManager.getCampID(registrationID);
            ArrayList<LocalDateTime> existingCampDates = Main.campManager.getCampDates(tempCampID);
    
            for (LocalDateTime newDate : newCampDates) {
                for (LocalDateTime existingDate : existingCampDates) {
                    if (newDate.equals(existingDate)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void addOnePointToStudent(String studentID) {
        Student student = (Student) userMap.get(studentID);
        student.addOnePoint();
    }
}
