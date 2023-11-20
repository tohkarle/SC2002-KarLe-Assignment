package cams.view.camp;

import cams.components.input.ConfirmOrDiscard;
import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.manager.CampManager;
import cams.model.Camp;
import cams.option.camp.CreatedCampInfoOptions;
import cams.ui.camp.DeleteCampUI;
import cams.utils.Dismiss;
import cams.utils.LoadingIndicator;
import cams.utils.ReportWriter;
import cams.view.enquiry.EnquiryStatusView;
import cams.view.report.GenerateRegistrationReportView;
import cams.view.suggestion.SuggestionStatusView;

public class CreatedCampInfoView implements View {

    private Navigation navigation;
    private int selectedCampID;
    private Camp camp;

    public CreatedCampInfoView(Navigation navigation, int selectedCampID) {
        this.navigation = navigation;
        this.selectedCampID = selectedCampID;
    }

    public void render() {

        CampManager campManager = CampManager.getInstance();
        
        this.camp = campManager.getCamp(selectedCampID);
        Options createdCampInfoOptions = new CreatedCampInfoOptions(camp);

        // Display created camp details
        createdCampInfoOptions.display("Camp details: ");

        // Allow staff to go back or edit camp, manage enquiries etc
        int option = createdCampInfoOptions.selection();
        if (option == Dismiss.intOption() ) { 
            navigation.dismissView();
            return; 
        }

        switch (option) {
            case 1:
                navigation.navigateTo(new EditCampView(navigation, camp));
                break;
            case 2:
                navigation.navigateTo(new EnquiryStatusView(navigation, selectedCampID));
                break;
            case 3:
                navigation.navigateTo(new SuggestionStatusView(navigation, selectedCampID));
                break;
            case 4:
                navigation.navigateTo(new GenerateRegistrationReportView(navigation, selectedCampID));
                break;
            case 5:
                if (new ConfirmOrDiscard().getValidInt("Confirm generate report?") != 1) { return; }
                ReportWriter.generatePerformanceReport(selectedCampID);
                LoadingIndicator.createLoadingIndicator("report");
                break;
            case 6:
                UI deleteCampUI = new DeleteCampUI(navigation, selectedCampID);
                deleteCampUI.body();
                break;
        }
    }
}
