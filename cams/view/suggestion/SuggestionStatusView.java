package cams.view.suggestion;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.manager.SuggestionManager;
import cams.manager.UserManager;
import cams.model.SuggestionStatus;
import cams.utils.Dismiss;

public class SuggestionStatusView extends View {

    private UserManager userManager;
    private SuggestionManager suggestionManager;

    // Options for this view:
    private Options suggestionStatusOptions;

    public SuggestionStatusView(Navigation navigation, UserManager userManager, SuggestionManager suggestionManager) {
        super(navigation);
        this.userManager = userManager;
        this.suggestionManager  = suggestionManager;
    }

    public void render() {
        // Display suggestion type options
        suggestionStatusOptions = super.getOptions("suggestion.SuggestionStatusOptions");
        suggestionStatusOptions.display("Select suggestion status:");

        // Let staff select which suggestion type to view
        int option = suggestionStatusOptions.selection();
        if (option == Dismiss.intOption()) {
            super.getNavigation().dismissView();
            return; 
        }

        // Set the selected status to display corresponding suggestions
        SuggestionStatus suggestionStatus = SuggestionStatus.PENDING;
        if (option == 1) { suggestionStatus = SuggestionStatus.PENDING; }
        if (option == 2) { suggestionStatus = SuggestionStatus.ACCEPTED; }
        if (option == 3) { suggestionStatus = SuggestionStatus.REJECTED; }
        suggestionManager.setSelectedSuggestionStatus(suggestionStatus);

        // Staff can see all suggestions for this camp
        // Committee can only see their own suggestions for this camp
        if (userManager.isStaff()) {
            super.getNavigation().navigateTo("suggestion.CampSuggestionsView");
        } else {
            super.getNavigation().navigateTo("suggestion.CommitteeCampSuggestionsView");
        }
    }
}
