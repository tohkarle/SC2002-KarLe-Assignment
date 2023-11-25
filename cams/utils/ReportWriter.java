package cams.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import cams.manager.CampManager;
import cams.manager.EnquiryManager;
import cams.manager.SuggestionManager;
import cams.model.Camp;
import cams.model.Enquiry;

/**
 * Utility class responsible for generating various types of reports related to camps.
 */
public class ReportWriter {

    /**
     * Generates a registration report for a specific camp, saved as a txt file.
     * The report includes details of the camp and a list of registered students with their roles.
     * @param campID The ID of the camp to generate the report for.
     * @param selectedReportOption The type of report to generate: 1 for attendee registration, 2 for committee registration, 3 for both.
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
            writeCampDetails(writer, camp);

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
     * Generates a performance report for a specific camp, saved as a txt file.
     * The report includes details of the camp and a list of committee members with their performance points.
     * @param campID The ID of the camp to generate the report for.
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
            writeCampDetails(writer, camp);
    
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


    /**
     * Generates an enquiry report for a specific camp, saved as a txt file.
     * The report includes details of the camp and a list of enquiries with their titles, contents, and replies.
     * @param campID The ID of the camp to generate the report for.
     * @param selectedReportOption The type of report to generate: 1 for resolved enquiries, 2 for unresolved enquiries, 3 for both.
     */
    public static void generateEnquiryReport(int campID, int selectedReportOption) {

        CampManager campManager = CampManager.getInstance();
        EnquiryManager enquiryManager = EnquiryManager.getInstance();

        Camp camp = campManager.getCamp(campID);
        String campName = camp.getCampName();

        ArrayList<Enquiry> enquiries = new ArrayList<>();
        String fileName = "";
        if (selectedReportOption == 1) {
            enquiries.addAll(enquiryManager.getAllResolvedCampEnquiries(campID));
            fileName = campName + "-resolved-enquiry";
        } else if (selectedReportOption == 2) {
            enquiries.addAll(enquiryManager.getAllNotResolvedCampEnquiries(campID));
            fileName = campName + "-unresolved-enquiry";
        } else if (selectedReportOption == 3) {
            enquiries.addAll(enquiryManager.getAllCampEnquiries(campID));
            fileName = campName + "-both-enquiry";
        }

        ArrayList<String> titles = new ArrayList<>();
        ArrayList<String> contents = new ArrayList<>();
        ArrayList<String> replies = new ArrayList<>();

        for (Enquiry enquiry : enquiries) {
            titles.add(enquiry.getTitle());
            contents.add(enquiry.getContent());
            replies.add(enquiry.getReply() != null ? enquiry.getReply() : "(Not resolved)");
        }
        
        String txtFileName = fileName.endsWith(".txt") ? fileName : fileName + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("cams/data/report/" + txtFileName))) {
            // Write camp details to text file
            writeCampDetails(writer, camp);

            // Write header line for student list
            writer.write("Title, Content, Reply\n");
            for (int i = 0; i < enquiries.size(); i++) {
                String line = String.format("%s, %s, %s\n", titles.get(i), contents.get(i), replies.get(i));
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Writes the details of a specific camp to a BufferedWriter.
     * The details include the camp name, faculty, location, description, visibility, dates, registration closing date, remaining slots, remaining committee slots, and staff-in-charge.
     * @param writer The BufferedWriter to write the camp details to.
     * @param camp The Camp object to get the details from.
     * @throws IOException If an I/O error occurs.
     */
    private static void writeCampDetails(BufferedWriter writer, Camp camp) throws IOException {
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
    }
}
