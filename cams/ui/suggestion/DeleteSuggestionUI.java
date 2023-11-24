package cams.ui.suggestion;

import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.SuggestionManager;
import cams.utils.LoadingIndicator;

/**
 * UI object for deleting a suggestion
 */
public class DeleteSuggestionUI implements UI {

    private Navigation navigation;
    private Input getInput;
    private SuggestionManager suggestionManager;
    private int selectedSuggestionID;

    /**
     * Initialize the DeleteSuggestionUI
     * @param navigation Navigation object used to control navigation of the application
     * @param getInput Input object used to get user input
     * @param suggestionManager SuggestionManager object used to manage suggestion data
     * @param selectedSuggestionID ID of the suggestion to be deleted
     */
    public DeleteSuggestionUI(Navigation navigation, Input getInput, SuggestionManager suggestionManager, int selectedSuggestionID) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.suggestionManager = suggestionManager;
        this.selectedSuggestionID = selectedSuggestionID;
    }

    /**
     * Executes user interaction logic for deleting a suggestion
     */
    @Override
    public void body() {

        // Confirm delete or discard and go back
        if (getInput.confirmOrDiscard("Confirm delete?") != 1) { return; }

        // Delete suggestion
        suggestionManager.deleteSuggestion(selectedSuggestionID);
        LoadingIndicator.deleteLoadingIndicator("suggestion");
        navigation.dismissView();
    }
}
