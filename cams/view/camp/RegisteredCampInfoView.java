package cams.view.camp;

import cams.components.option.Options;
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
import cams.view.suggestion.CreateSuggestionView;
import cams.view.suggestion.SuggestionStatusView;

public class RegisteredCampInfoView implements View {

    private Camp camp;
    private Navigation navigation;
    private int selectedCampID;
    private UserManager userManager;
    private CampManager campManager;
    private Options registeredCampInfoOptions;

    public RegisteredCampInfoView(Navigation navigation, int selectedCampID) {
        this.navigation = navigation;
        this.selectedCampID = selectedCampID;
    }

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

    public boolean studentIsCommitteeForThisCamp() {
        return campManager.isACommitteeMemberOfThisCamp(userManager.getCurrentUser().getName(), selectedCampID);
    }

    public void attendeeOptions() {
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
                navigation.navigateTo(new EnquiryStatusView(navigation, selectedCampID));
                break;
            case 3:
                UI withdrawFromCampUI = new WithdrawFromCampUI(navigation, userManager, campManager, selectedCampID);
                withdrawFromCampUI.body();
                break;
        }
    }

    public void committeeOptions() {

        // Allow committee to manage camp enquiries, create suggestion or manage their suggestions
        int option = registeredCampInfoOptions.selection();
        if (option == Dismiss.intOption() ) { 
            navigation.dismissView();
            return; 
        }

        switch(option) {
            case 1:
                navigation.navigateTo(new EnquiryStatusView(navigation, selectedCampID));
                break;
            case 2:
                navigation.navigateTo(new CreateSuggestionView(navigation, camp));
                break;
            case 3:
                navigation.navigateTo(new SuggestionStatusView(navigation, selectedCampID));
                break;
        }
    }
}
