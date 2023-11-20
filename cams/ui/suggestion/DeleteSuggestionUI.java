package cams.ui.suggestion;

import cams.components.input.ConfirmOrDiscard;
import cams.interfaces.IntInput;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.SuggestionManager;
import cams.utils.LoadingIndicator;

public class DeleteSuggestionUI implements UI {

    private Navigation navigation;
    private SuggestionManager suggestionManager;
    private int selectedSuggestionID;

    public DeleteSuggestionUI(Navigation navigation, SuggestionManager suggestionManager, int selectedSuggestionID) {
        this.navigation = navigation;
        this.suggestionManager = suggestionManager;
        this.selectedSuggestionID = selectedSuggestionID;
    }

    @Override
    public void body() {
        IntInput confirm = new ConfirmOrDiscard();

        // Confirm delete or discard and go back
        if (confirm.getValidInt("Confirm delete?") != 1) { return; }

        // Delete suggestion
        suggestionManager.deleteSuggestion(selectedSuggestionID);
        LoadingIndicator.deleteLoadingIndicator("suggestion");
        navigation.dismissView();
    }
}
