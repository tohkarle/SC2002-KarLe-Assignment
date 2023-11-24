package cams.view.report;

import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.option.report.GenerateReportOptions;
import cams.utils.Dismiss;
import cams.utils.LoadingIndicator;
import cams.utils.ReportWriter;

/**
 * View object for Generate Registration Report page
 */
public class GenerateRegistrationReportView implements View {

    private Navigation navigation;
    private Input getInput;
    private int selectedCampID;

    /**
     * Initialize the GenerateRegistrationReportView
     * @param navigation Navigation object used to control navigation of the application
     * @param getInput Input object used to get input from user
     * @param selectedCampID int of the selected camp ID
     */
    public GenerateRegistrationReportView(Navigation navigation, Input getInput, int selectedCampID) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.selectedCampID = selectedCampID;
    }

    /**
     * Render the GenerateRegistrationReportView
     */
    public void render() {

        Options generateReportOptions = new GenerateReportOptions();
        generateReportOptions.display("Choose an option: ");

        int selectedReportOption = generateReportOptions.selection();
        if (selectedReportOption == Dismiss.intOption()) { 
            navigation.dismissView();
            return; 
        }

        if (getInput.confirmOrDiscard("Confirm generate report?") != 1) { return; };
        ReportWriter.generateRegistrationReport(selectedCampID, selectedReportOption);
        LoadingIndicator.createLoadingIndicator("report");
    }
}
