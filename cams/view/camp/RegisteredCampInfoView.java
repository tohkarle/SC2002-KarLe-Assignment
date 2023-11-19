package cams.view.camp;

import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.manager.CampManager;
import cams.manager.UserManager;
import cams.option.camp.CampInfoOptions;
import cams.option.camp.RegisteredCampInfoOptions;
import cams.utils.Dismiss;

public class RegisteredCampInfoView extends View {

    private UserManager userManager;
    private CampManager campManager;
    
    // Options for this view:
    private CampInfoOptions registeredCampInfoOptions;

    // UIs for this view:
    private UI withdrawFromCampUI;

    public RegisteredCampInfoView(Navigation navigation, UserManager userManager, CampManager campManager) {
        super(navigation);
        this.userManager = userManager;
        this.campManager = campManager;
    }

    public void render() {

        registeredCampInfoOptions = (RegisteredCampInfoOptions) super.getOptions("camp.RegisteredCampInfoOptions");

        // Update registered camp details to latest
        registeredCampInfoOptions.updateCampInfo();

        // Display registered camp details
        registeredCampInfoOptions.display("Camp details: ");



        if (studentIsCommitteeForThisCamp()) {
            createOrManageSuggestion();
        } else {
            withdrawStudent();
        }
    }

    public boolean studentIsCommitteeForThisCamp() {
        return campManager.isACommitteeMemberOfThisCamp(userManager.getCurrentUser().getName(), campManager.getSelectedCampID());
    }

    public void withdrawStudent() {
        // Allow student to go back or withdraw from camp
        int option = registeredCampInfoOptions.selection();
        if (option == Dismiss.intOption() ) { 
            super.getNavigation().dismissView();
            return; 
        }

        switch(option) {
            case 1:
                super.getNavigation().navigateTo("enquiry.CreateEnquiryView");
                break;
            case 2:
                super.getNavigation().navigateTo("enquiry.EnquiryStatusView");
                break;
            case 3:
                withdrawFromCampUI = super.getUI("camp.WithdrawFromCampUI");
                withdrawFromCampUI.body();
                break;
        }
    }

    public void createOrManageSuggestion() {

        // Allow committee to go create or manage suggestion
        int option = registeredCampInfoOptions.selection();
        if (option == Dismiss.intOption() ) { 
            super.getNavigation().dismissView();
            return; 
        }

        switch(option) {
            case 1:
                super.getNavigation().navigateTo("enquiry.EnquiryStatusView");
                break;
            case 2:
                super.getNavigation().navigateTo("suggestion.CreateSuggestionView");
                break;
            case 3:
                super.getNavigation().navigateTo("suggestion.SuggestionStatusView");
                break;
        }
    }
}
