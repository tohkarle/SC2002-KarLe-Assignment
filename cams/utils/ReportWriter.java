package cams.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import cams.manager.CampManager;
import cams.manager.EnquiryManager;
import cams.manager.SuggestionManager;

public class ReportWriter {

    /**
     * Generates a report for the specified list of camps, saved as a CSV file
     * @param camID The id of camps to generate the report for
     */
    public static void generateRegistrationReport(int campID, int selectedReportOption) {

        CampManager campManager = CampManager.getInstance();
        String campName = campManager.getCampName(campID);
        ArrayList<String> studentNames = new ArrayList<>();
        String fileName = "";
        if (selectedReportOption == 1) {
            fileName = campName + "-attendee-registration";
            studentNames.addAll(campManager.getParticipatingStudentNames(campID));
        } else if (selectedReportOption == 2) {
            fileName = campName + "-committee-registration";
            studentNames.addAll(campManager.getCommitteeMemberNames(campID));
        } else if (selectedReportOption == 3) {
            fileName = campName + "-both-registration";
            studentNames.addAll(campManager.getParticipatingStudentNames(campID));
            studentNames.addAll(campManager.getCommitteeMemberNames(campID));
        }

        String csvFileName = fileName.endsWith(".csv") ? fileName : fileName + ".csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("cams/data/report/" + csvFileName))) {

            // Write header line to CSV
            writer.write("Student Name\n");

            for (String studentName : studentNames) {
                String line = String.format(
                        "%s\n",
                        studentName
                );
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

        String campName = campManager.getCampName(campID);
        String fileName = campName + "-committee-performance";
        String csvFileName = fileName.endsWith(".csv") ? fileName : fileName + ".csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("cams/data/report/" + csvFileName))) {
            // Write header line to CSV
            writer.write("Committee Member Name,Points\n");

            ArrayList<String> committeeNames = campManager.getCommitteeMemberNames(campID);

            for (String committeeName : committeeNames) {
                numberOfEnquiriesReplied = enquiryManager.getNumberOfEnquiriesReplied(committeeName);
                numberOfSuggestionsGiven = suggestionManager.getNumberOfSuggestionsGiven(committeeName);
                numberOfSuggestionsApproved = suggestionManager.getNumberOfSuggestionsApproved(committeeName);
                points = numberOfEnquiriesReplied + numberOfSuggestionsGiven + numberOfSuggestionsApproved;

                String line = String.format(
                        "%s,%d\n",
                        committeeName,
                        points
                );
                writer.write(line);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
