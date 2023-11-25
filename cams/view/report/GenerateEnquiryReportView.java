package cams.view.report;

import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.option.report.GenerateEnquiryReportOptions;
import cams.utils.Dismiss;
import cams.utils.LoadingIndicator;
import cams.utils.ReportWriter;

/**
 * View object for Generate Enquiry Report page
 */
public class GenerateEnquiryReportView implements View{

    private Navigation navigation;
    private Input getInput;
    private int selectedCampID;

    /**
     * Initialize the GenerateEnquiryReportView
     * @param navigation Navigation object used to control navigation of the application
     * @param getInput Input object used to get input from user
     * @param selectedCampID int of the selected camp ID
     */
    public GenerateEnquiryReportView(Navigation navigation, Input getInput, int selectedCampID) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.selectedCampID = selectedCampID;
    }

    /**
     * Render the GenerateEnquiryReportView
     */
    public void render() {

        Options generateEnquiryReportOptions = new GenerateEnquiryReportOptions();
        generateEnquiryReportOptions.display("Choose an option: ");

        int selectedReportOption = generateEnquiryReportOptions.selection();
        if (selectedReportOption == Dismiss.intOption()) { 
            navigation.dismissView();
            return; 
        }

        if (getInput.confirmOrDiscard("Confirm generate report?") != 1) { return; };
        ReportWriter.generateEnquiryReport(selectedCampID, selectedReportOption);
        LoadingIndicator.createLoadingIndicator("report");
    }
}
