package cams.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import cams.manager.CampManager;
import cams.manager.EnquiryManager;
import cams.manager.SuggestionManager;
import cams.model.Camp;

public class ReportWriter {

    /**
     * Generates a report for the specified list of camps, saved as a CSV file
     * @param camID The id of camps to generate the report for
     */
    public static void generateRegistrationReport(int campID, int selectedReportOption) {

        CampManager campManager = CampManager.getInstance();
        Camp camp = campManager.getCamp(campID);
        ArrayList<String> studentNames = new ArrayList<>();
        ArrayList<String> roles = new ArrayList<>();

        String fileName = "";
        if (selectedReportOption == 1) {
            fileName = camp.getCampName() + "-attendee-registration";
            studentNames.addAll(campManager.getParticipatingStudentNames(campID));
            for (int i = 0; i < studentNames.size(); i++) {
                roles.add("Attendee");
            }
        } else if (selectedReportOption == 2) {
            fileName = camp.getCampName() + "-committee-registration";
            studentNames.addAll(campManager.getCommitteeMemberNames(campID));
            for (int i = 0; i < studentNames.size(); i++) {
                roles.add("Committee");
            }
        } else if (selectedReportOption == 3) {
            fileName = camp.getCampName() + "-both-registration";
            studentNames.addAll(campManager.getParticipatingStudentNames(campID));
            for (int i = 0; i < studentNames.size(); i++) {
                roles.add("Attendee");
            }
            int attendeeCount = studentNames.size();
            studentNames.addAll(campManager.getCommitteeMemberNames(campID));
            for (int i = attendeeCount; i < studentNames.size(); i++) {
                roles.add("Committee");
            }
        }

        String txtFileName = fileName.endsWith(".txt") ? fileName : fileName + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("cams/data/report/" + txtFileName))) {
            // Write camp details to text file
            writer.write(String.format("Camp name: %s\n", camp.getCampName()));
            writer.write(String.format("Faculty: %s\n", camp.getUserGroup()));
            writer.write(String.format("Location: %s\n", camp.getLocation()));
            writer.write(String.format("Description: %s\n", camp.getDescription()));
            writer.write(String.format("Visibility: %s\n", camp.getVisibility() ? "On" : "Off"));
            writer.write(String.format("Dates: %s to %s\n", camp.getStartDate(), camp.getEndDate()));
            writer.write(String.format("Registration closing date: %s\n", camp.getRegistrationClosingDate()));
            writer.write(String.format("Remaining slots: %d / %d\n", (camp.getTotalSlots() - (camp.getParticipatingStudentNames().size() + camp.getCommitteeMemberNames().size())), camp.getTotalSlots()));
            writer.write(String.format("Remaining committee slots: %d / %d\n", (camp.getCommitteeSlots() - camp.getCommitteeMemberNames().size()), camp.getCommitteeSlots()));
            writer.write(String.format("Staff-in-charge: %s\n\n", camp.getStaffInCharge()));

            // Write header line for student list
            writer.write("Student Name, Role\n");
            for (int i = 0; i < studentNames.size(); i++) {
                String line = String.format("%s, %s\n", studentNames.get(i), roles.get(i));
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates a performance report (Points) for the specified list of camps, saved as a CSV file
     * @param campList The list of camps to generate the report for
     * @param reportName The name of the report to be generated
     */
    public static void generatePerformanceReport(int campID) {
        CampManager campManager = CampManager.getInstance();
        EnquiryManager enquiryManager = EnquiryManager.getInstance();
        SuggestionManager suggestionManager = SuggestionManager.getInstance();
    
        int numberOfEnquiriesReplied = -1;
        int numberOfSuggestionsGiven = -1; 
        int numberOfSuggestionsApproved = -1;
        int points = -1;
    
        Camp camp = campManager.getCamp(campID);
        String campName = camp.getCampName();
        String fileName = campName + "-committee-performance";
        String txtFileName = fileName.endsWith(".txt") ? fileName : fileName + ".txt";
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("cams/data/report/" + txtFileName))) {
            // Write camp details to text file
            writer.write(String.format("Camp name: %s\n", camp.getCampName()));
            writer.write(String.format("Faculty: %s\n", camp.getUserGroup()));
            writer.write(String.format("Location: %s\n", camp.getLocation()));
            writer.write(String.format("Description: %s\n", camp.getDescription()));
            writer.write(String.format("Visibility: %s\n", camp.getVisibility() ? "On" : "Off"));
            writer.write(String.format("Dates: %s to %s\n", camp.getStartDate(), camp.getEndDate()));
            writer.write(String.format("Registration closing date: %s\n", camp.getRegistrationClosingDate()));
            writer.write(String.format("Remaining slots: %d / %d\n", (camp.getTotalSlots() - (camp.getParticipatingStudentNames().size() + camp.getCommitteeMemberNames().size())), camp.getTotalSlots()));
            writer.write(String.format("Remaining committee slots: %d / %d\n", (camp.getCommitteeSlots() - camp.getCommitteeMemberNames().size()), camp.getCommitteeSlots()));
            writer.write(String.format("Staff-in-charge: %s\n\n", camp.getStaffInCharge()));
    
            // Write header line for committee performance
            writer.write("\nCommittee Member Name, Points\n");
    
            ArrayList<String> committeeNames = campManager.getCommitteeMemberNames(campID);
    
            for (String committeeName : committeeNames) {
                numberOfEnquiriesReplied = enquiryManager.getNumberOfEnquiriesReplied(committeeName);
                numberOfSuggestionsGiven = suggestionManager.getNumberOfSuggestionsGiven(committeeName);
                numberOfSuggestionsApproved = suggestionManager.getNumberOfSuggestionsApproved(committeeName);
                points = numberOfEnquiriesReplied + numberOfSuggestionsGiven + numberOfSuggestionsApproved;
    
                String line = String.format("%s, %d\n", committeeName, points);
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
