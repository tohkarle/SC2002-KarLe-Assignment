package cams.ui.suggestion;

import cams.components.LoadingIndicator;
import cams.interfaces.IntInput;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.SuggestionManager;

public class DeleteSuggestionUI implements UI {

    private Navigation navigation;
    private SuggestionManager suggestionManager;
    private IntInput confirm;

    public DeleteSuggestionUI(Navigation navigation, SuggestionManager suggestionManager, IntInput confirm) {
        this.navigation = navigation;
        this.suggestionManager = suggestionManager;
        this.confirm = confirm;
    }

    @Override
    public void body() {
        // Confirm delete or discard and go back
        if (confirm.getValidInt("Confirm delete?") != 1) { return; }

        // Delete suggestion
        suggestionManager.deleteSuggestion(suggestionManager.getSelectedSuggestionID());
        LoadingIndicator.deleteLoadingIndicator("suggestion");
        navigation.dismissView();
    }
}
