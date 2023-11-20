package cams.view.suggestion;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.model.Suggestion;
import cams.option.suggestion.EditSuggestionOptions;
import cams.ui.suggestion.EditSuggestionUI;
import cams.utils.Dismiss;

public class EditSuggestionView implements View {

    private Navigation navigation;
    private Suggestion suggestion;

    public EditSuggestionView(Navigation navigation, Suggestion suggestion) {
        this.navigation = navigation;
        this.suggestion = suggestion;
    }

    public void render() {

        Options editSuggestionOptions = new EditSuggestionOptions(suggestion);

        // Display suggestion info for editing
        editSuggestionOptions.display("Select the field you want to edit: ");

        // Let user choose the field to edit
        int selectedCampInfo = editSuggestionOptions.selection();
        if (selectedCampInfo == Dismiss.intOption()) { 
            navigation.dismissView();
            return; 
        }

        UI editSuggestionUI = new EditSuggestionUI(navigation, suggestion, selectedCampInfo);
        editSuggestionUI.body();
    }
}
