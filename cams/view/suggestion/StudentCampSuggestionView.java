package cams.view.suggestion;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.manager.SuggestionManager;
import cams.utils.Dismiss;

public class StudentCampSuggestionView extends View {

    private SuggestionManager suggestionManager;

    // Options for this view:
    private Options studentCampSuggestionOptions;

    public StudentCampSuggestionView(Navigation navigation, SuggestionManager suggestionManager) {
        super(navigation);
        this.suggestionManager = suggestionManager;
    }

    public void render() {
        // Display suuggestions
        studentCampSuggestionOptions = super.getOptions("suggestion.StudentCampSuggestionOptions");
        studentCampSuggestionOptions.display("Select suggestion to view details:");

        // Let user select suggestion to view details
        int option = studentCampSuggestionOptions.selection();
        if (option == Dismiss.intOption()) { 
            super.getNavigation().dismissView();
            return; 
        }
        suggestionManager.setSelectedSuggestionID(option);

        // Navigate to CampInfoView
        super.getNavigation().navigateTo("suggestion.CreatedSuggestionInfoView");
    }
}
