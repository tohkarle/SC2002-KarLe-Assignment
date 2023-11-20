package cams.ui.report;

import java.util.ArrayList;

import cams.components.input.ConfirmOrDiscard;
import cams.interfaces.UI;
import cams.manager.CampManager;
import cams.utils.LoadingIndicator;
import cams.utils.ReportWriter;

public class GenerateRegistrationReportUI implements UI {

    private int selectedReportOption;
    private int selectedCampID;

    public GenerateRegistrationReportUI(int selectedReportOption, int selectedCampID) {
        this.selectedReportOption = selectedReportOption;
        this.selectedCampID = selectedCampID;
    }

    public void body() {

        CampManager campManager = CampManager.getInstance();
        String campName = campManager.getCampName(selectedCampID);

        if (new ConfirmOrDiscard().getValidInt("Confirm generate report?") != 1) { return; };

        String fileName = "";
        ArrayList<String> studentNames = new ArrayList<>();
        if (selectedReportOption == 1) {
            fileName = campName + "-attendee";
            studentNames.addAll(campManager.getParticipatingStudentNames(selectedCampID));
        } else if (selectedReportOption == 2) {
            fileName = campName + "-committee";
            studentNames.addAll(campManager.getCommitteeMemberNames(selectedCampID));
        } else if (selectedReportOption == 3) {
            fileName = campName + "-both";
            studentNames.addAll(campManager.getParticipatingStudentNames(selectedCampID));
            studentNames.addAll(campManager.getCommitteeMemberNames(selectedCampID));
        }

        ReportWriter.generateRegistrationReport(fileName, studentNames);
        LoadingIndicator.createLoadingIndicator("report");
    }
}
