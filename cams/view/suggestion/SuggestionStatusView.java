package cams.view.suggestion;

import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.manager.UserManager;
import cams.model.SuggestionStatus;
import cams.option.suggestion.SuggestionStatusOptions;
import cams.utils.Dismiss;

public class SuggestionStatusView implements View {

    private Navigation navigation;
    private Input getInput;
    private int selectedCampID;

    public SuggestionStatusView(Navigation navigation, Input getInput, int selectedCampID) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.selectedCampID = selectedCampID;
    }

    public void render() {

        UserManager userManager = UserManager.getInstance();

        // Display suggestion type options
        Options suggestionStatusOptions = new SuggestionStatusOptions();
        suggestionStatusOptions.display("Select suggestion status:");

        // Let staff select which suggestion type to view
        int option = suggestionStatusOptions.selection();
        if (option == Dismiss.intOption()) {
            navigation.dismissView();
            return; 
        }

        // Set the selected status to display corresponding suggestions
        SuggestionStatus suggestionStatus = SuggestionStatus.PENDING;
        if (option == 1) { suggestionStatus = SuggestionStatus.PENDING; }
        if (option == 2) { suggestionStatus = SuggestionStatus.ACCEPTED; }
        if (option == 3) { suggestionStatus = SuggestionStatus.REJECTED; }

        // Staff can see all suggestions for this camp
        // Committee can only see their own suggestions for this camp
        if (userManager.isStaff()) {
            navigation.navigateTo(new CampSuggestionsView(navigation, getInput, suggestionStatus, selectedCampID));
        } else {
            navigation.navigateTo(new CommitteeCampSuggestionsView(navigation, getInput, suggestionStatus, selectedCampID));
        }
    }
}
