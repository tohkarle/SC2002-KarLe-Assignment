package cams.view.camp;

import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.manager.CampManager;
import cams.manager.UserManager;
import cams.model.Camp;
import cams.option.camp.RegisteredCampInfoOptions;
import cams.ui.camp.WithdrawFromCampUI;
import cams.utils.Dismiss;
import cams.view.enquiry.CreateEnquiryView;
import cams.view.enquiry.EnquiryStatusView;
import cams.view.report.GenerateRegistrationReportView;
import cams.view.suggestion.CreateSuggestionView;
import cams.view.suggestion.SuggestionStatusView;

/**
 * View object for Registered Camp Info page
 */
public class RegisteredCampInfoView implements View {

    private Navigation navigation;
    private Input getInput;
    private Camp camp;
    private int selectedCampID;
    private UserManager userManager;
    private CampManager campManager;
    private Options registeredCampInfoOptions;

    /**
     * Initialize the RegisteredCampInfoView
     * @param navigation Navigation object used to control navigation of the application
     * @param getInput Input object used to get input from user
     * @param selectedCampID int of the selected camp ID
     */
    public RegisteredCampInfoView(Navigation navigation, Input getInput, int selectedCampID) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.selectedCampID = selectedCampID;
    }

    /**
     * Render the RegisteredCampInfoView
     */
    public void render() {

        userManager = UserManager.getInstance();
        campManager = CampManager.getInstance();
        camp = campManager.getCamp(selectedCampID);
        registeredCampInfoOptions = new RegisteredCampInfoOptions(camp);

        // Display registered camp details
        registeredCampInfoOptions.display("Camp details: ");

        if (studentIsCommitteeForThisCamp()) {
            committeeOptions();
        } else {
            attendeeOptions();
        }
    }

    /**
     * Check if student is a committee for this camp
     * @return boolean true if student is a committee for this camp, false otherwise
     */
    private boolean studentIsCommitteeForThisCamp() {
        return campManager.isACommitteeMemberOfThisCamp(userManager.getCurrentUser().getName(), selectedCampID);
    }

    /**
     * Display options for attendee
     */
    private void attendeeOptions() {
        // Allow student to go back, create enquiry, manage their enquiries or withdraw from camp
        int option = registeredCampInfoOptions.selection();
        if (option == Dismiss.intOption() ) { 
            navigation.dismissView();
            return; 
        }

        switch(option) {
            case 1:
                navigation.navigateTo(new CreateEnquiryView(navigation, selectedCampID));
                break;
            case 2:
                navigation.navigateTo(new EnquiryStatusView(navigation, getInput, selectedCampID));
                break;
            case 3:
                UI withdrawFromCampUI = new WithdrawFromCampUI(navigation, userManager, campManager, selectedCampID);
                withdrawFromCampUI.body();
                break;
        }
    }

    /**
     * Display options for committee
     */
    private void committeeOptions() {

        // Allow committee to manage camp enquiries, create suggestion or manage their suggestions
        int option = registeredCampInfoOptions.selection();
        if (option == Dismiss.intOption() ) { 
            navigation.dismissView();
            return; 
        }

        switch(option) {
            case 1:
                navigation.navigateTo(new EnquiryStatusView(navigation, getInput, selectedCampID));
                break;
            case 2:
                navigation.navigateTo(new CreateSuggestionView(navigation, getInput, camp));
                break;
            case 3:
                navigation.navigateTo(new SuggestionStatusView(navigation, getInput, selectedCampID));
                break;
            case 4:
                navigation.navigateTo(new GenerateRegistrationReportView(navigation, getInput, selectedCampID));
                break;
        }
    }
}
