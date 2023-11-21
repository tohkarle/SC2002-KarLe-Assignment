package cams.view.user;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.manager.CampManager;
import cams.manager.EnquiryManager;
import cams.manager.SuggestionManager;
import cams.manager.UserManager;
import cams.option.user.ProfileOptions;
import cams.utils.Dismiss;

public class ProfileView implements View {

    private Navigation navigation;
    private UserManager userManager;
    private CampManager campManager;
    private EnquiryManager enquiryManager;
    private SuggestionManager suggestionManager;

    public ProfileView(Navigation navigation) {
        this.navigation = navigation;
        this.userManager = UserManager.getInstance();
        this.campManager = CampManager.getInstance();
        this.enquiryManager = EnquiryManager.getInstance();
        this.suggestionManager = SuggestionManager.getInstance();
    }

    public void render() {
        
        // Display profile
        Options profileOptions = new ProfileOptions();
        profileOptions.display("Profile: ");

        if (userManager.isStaff()) {
            staffSpecificProfile();
        } else {
            studentSpecificProfile();
        }

        int option = profileOptions.selection();
        if (option == Dismiss.intOption()) { 
            navigation.dismissView();
            return;
        }
    }

    public void studentSpecificProfile() {

        // KARLE_TODO: CHANGE THIS SHIT
        int numberOfEnquiriesReplied = enquiryManager.getNumberOfEnquiriesReplied(userManager.getCurrentUser().getName());
        int numberOfSuggestionsGiven = suggestionManager.getNumberOfSuggestionsGiven(userManager.getCurrentUser().getName());
        int numberOfSuggestionsApproved = suggestionManager.getNumberOfSuggestionsApproved(userManager.getCurrentUser().getName());
        System.out.println("Point: " + (numberOfEnquiriesReplied + numberOfSuggestionsGiven + numberOfSuggestionsApproved));
        String campName = campManager.committeeMemberFor(userManager.getCurrentUser().getName());
        if (campName != null) {
            System.out.println("Committee member for: " + campName);
        }
    }

    public void staffSpecificProfile() {

    }
}
