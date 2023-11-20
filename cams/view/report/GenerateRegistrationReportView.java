package cams.view.report;

import cams.components.input.ConfirmOrDiscard;
import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.option.report.GenerateReportOptions;
import cams.utils.Dismiss;
import cams.utils.LoadingIndicator;
import cams.utils.ReportWriter;

public class GenerateRegistrationReportView implements View {

    private Navigation navigation;
    private int selectedCampID;

    public GenerateRegistrationReportView(Navigation navigation, int selectedCampID) {
        this.navigation = navigation;
        this.selectedCampID = selectedCampID;
    }

    public void render() {

        Options generateReportOptions = new GenerateReportOptions();
        generateReportOptions.display("Choose an option: ");

        int selectedReportOption = generateReportOptions.selection();
        if (selectedReportOption == Dismiss.intOption()) { 
            navigation.dismissView();
            return; 
        }

        if (new ConfirmOrDiscard().getValidInt("Confirm generate report?") != 1) { return; };
        ReportWriter.generateRegistrationReport(selectedCampID, selectedReportOption);
        LoadingIndicator.createLoadingIndicator("report");
    }
}
