package cams.view.suggestion;

import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.manager.SuggestionManager;
import cams.model.Suggestion;
import cams.model.SuggestionStatus;
import cams.option.suggestion.ProcessSuggestionOptions;
import cams.ui.suggestion.ProcessSuggestionUI;
import cams.utils.Dismiss;

/**
 * View object for Process Suggestion page
 */
public class ProcessSuggestionView implements View {

    private Navigation navigation;
    private Input getInput;
    private int selectedSuggestionID;
    private SuggestionStatus suggestionStatus;

    /**
     * Initialize the ProcessSuggestionView
     * @param navigation Navigation object used to control navigation of the application
     * @param getInput Input object used to get input from user
     * @param selectedSuggestionID int of the selected suggestion ID
     * @param suggestionStatus The status of the suggestions to view
     */
    public ProcessSuggestionView(Navigation navigation, Input getInput, int selectedSuggestionID, SuggestionStatus suggestionStatus) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.selectedSuggestionID = selectedSuggestionID;
        this.suggestionStatus = suggestionStatus;
}

    /**
     * Render the ProcessSuggestionView
     */
    public void render() {
        SuggestionManager suggestionManager = SuggestionManager.getInstance();
        Suggestion suggestion = suggestionManager.getSuggestion(selectedSuggestionID);
        Options processSuggestionOptions = new ProcessSuggestionOptions(suggestion, suggestionStatus);

        // Display suggestion details
        processSuggestionOptions.display("Suggestion details: ");

        if (suggestionStatus == SuggestionStatus.PENDING) {
            // Allo process suggestion if suggestion is pending
            UI processSuggestionUI = new ProcessSuggestionUI(navigation, getInput, suggestion, selectedSuggestionID);
            processSuggestionUI.body();
        } else {
            // Let staff view details and go back only
            if (processSuggestionOptions.selection() == Dismiss.intOption()) {
                navigation.dismissView();
            }
        }
    }
}
