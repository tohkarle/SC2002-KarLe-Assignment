package cams.view.user;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.manager.CampManager;
import cams.manager.EnquiryManager;
import cams.manager.SuggestionManager;
import cams.manager.UserManager;
import cams.utils.Dismiss;

public class ProfileView extends View {

    private UserManager userManager;
    private CampManager campManager;
    private EnquiryManager enquiryManager;
    private SuggestionManager suggestionManager;

    // Options:
    private Options profileOptions;

    // No UI for this view

    public ProfileView(Navigation navigation, UserManager userManager, CampManager campManager, EnquiryManager enquiryManager, SuggestionManager suggestionManager) {
        super(navigation);
        this.userManager = userManager;
        this.campManager = campManager;
        this.enquiryManager = enquiryManager;
        this.suggestionManager = suggestionManager;
    }

    public void render() {

        // Display profile
        profileOptions = super.getOptions("user.ProfileOptions");
        profileOptions.display("Profile: ");

        if (userManager.isStaff()) {
            staffSpecificProfile();
        } else {
            studentSpecificProfile();
        }

        int option = profileOptions.selection();
        if (option == Dismiss.intOption()) { 
            super.getNavigation().dismissView();
            return;
        }
    }

    public void studentSpecificProfile() {
        int numberOfEnquiriesReplied = enquiryManager.getNumberOfEnquiriesReplied(userManager.getCurrentUser().getName());
        int numberOfSuggestionsApproved = suggestionManager.getNumberOfSuggestionsApproved(userManager.getCurrentUser().getName());
        System.out.println("Point: " + (numberOfEnquiriesReplied + numberOfSuggestionsApproved));
        String campName = campManager.committeeMemberFor(userManager.getCurrentUser().getName());
        if (campName != null) {
            System.out.println("Committee member for: " + campName);
        }
    }

    public void staffSpecificProfile() {

    }
}
