package cams.view.suggestion;

import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.model.Suggestion;
import cams.option.suggestion.EditSuggestionOptions;
import cams.ui.suggestion.EditSuggestionUI;
import cams.utils.Dismiss;

/**
 * View object for Edit Suggestion page
 */
public class EditSuggestionView implements View {

    private Navigation navigation;
    private Input getInput;
    private Suggestion suggestion;

    /**
     * Initialize the EditSuggestionView
     * @param navigation Navigation object used to control navigation of the application
     * @param getInput Input object used to get input from user
     * @param suggestion Suggestion object to be edited
     */
    public EditSuggestionView(Navigation navigation, Input getInput, Suggestion suggestion) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.suggestion = suggestion;
    }

    /**
     * Render the EditSuggestionView
     */
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

        UI editSuggestionUI = new EditSuggestionUI(navigation, getInput, suggestion, selectedCampInfo);
        editSuggestionUI.body();
    }
}
