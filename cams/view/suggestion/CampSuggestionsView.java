package cams.view.suggestion;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.manager.SuggestionManager;
import cams.utils.Dismiss;

public class CampSuggestionsView extends View {

    private SuggestionManager suggestionManager;

    // Options for this view:
    private Options campSuggestionOptions;

    public CampSuggestionsView(Navigation navigation, SuggestionManager suggestionManager) {
        super(navigation);
        this.suggestionManager  = suggestionManager;
    }

    public void render() {
        // Display suggestion
        campSuggestionOptions = super.getOptions("suggestion.CampSuggestionOptions");
        campSuggestionOptions.display("Select camp to view details:");

        // Let user select camp to view details
        int option = campSuggestionOptions.selection();
        if (option == Dismiss.intOption()) { 
            super.getNavigation().dismissView();
            return; 
        }
        suggestionManager.setSelectedSuggestionID(option);

        // Navigate to CampInfoView
        super.getNavigation().navigateTo("suggestion.ProcessSuggestionView");
    }
}
