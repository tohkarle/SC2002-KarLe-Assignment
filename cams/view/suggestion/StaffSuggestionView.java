package cams.view.suggestion;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.manager.SuggestionManager;
import cams.model.SuggestionStatus;
import cams.utils.Dismiss;

public class StaffSuggestionView extends View {

    private SuggestionManager suggestionManager;

    // Options for this view:
    private Options staffSuggestOptions;

    public StaffSuggestionView(Navigation navigation, SuggestionManager suggestionManager) {
        super(navigation);
        this.suggestionManager  = suggestionManager;
    }

    public void render() {
        // Display suggestion type options
        staffSuggestOptions = super.getOptions("suggestion.StaffSuggestionOptions");
        staffSuggestOptions.display("Select suggestion status:");

        // Let staff select which suggestion type to view
        int option = staffSuggestOptions.selection();
        if (option == Dismiss.intOption()) {
            super.getNavigation().dismissView();
            return; 
        }

        // Navigate to respective view
        SuggestionStatus suggestionStatus = SuggestionStatus.PENDING;
        if (option == 1) { suggestionStatus = SuggestionStatus.PENDING; }
        if (option == 2) { suggestionStatus = SuggestionStatus.ACCEPTED; }
        if (option == 3) { suggestionStatus = SuggestionStatus.REJECTED; }
        suggestionManager.setSelectedSuggestionStatus(suggestionStatus);
        super.getNavigation().navigateTo("suggestion.CampSuggestionsView");
    }
    
}
