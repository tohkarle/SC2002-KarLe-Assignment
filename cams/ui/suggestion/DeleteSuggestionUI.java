package cams.ui.suggestion;

import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.SuggestionManager;
import cams.utils.LoadingIndicator;

public class DeleteSuggestionUI implements UI {

    private Navigation navigation;
    private Input getInput;
    private SuggestionManager suggestionManager;
    private int selectedSuggestionID;

    public DeleteSuggestionUI(Navigation navigation, Input getInput, SuggestionManager suggestionManager, int selectedSuggestionID) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.suggestionManager = suggestionManager;
        this.selectedSuggestionID = selectedSuggestionID;
    }

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
