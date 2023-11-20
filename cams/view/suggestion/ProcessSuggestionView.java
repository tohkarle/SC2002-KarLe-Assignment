package cams.view.suggestion;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.manager.SuggestionManager;
import cams.model.Suggestion;
import cams.model.SuggestionStatus;
import cams.option.suggestion.ProcessSuggestionOptions;
import cams.ui.suggestion.ProcessSuggestionUI;
import cams.utils.Dismiss;

public class ProcessSuggestionView implements View {

    private Navigation navigation;
    private int selectedSuggestionID;
    private SuggestionStatus suggestionStatus;

    public ProcessSuggestionView(Navigation navigation, int selectedSuggestionID, SuggestionStatus suggestionStatus) {
        this.navigation = navigation;
        this.selectedSuggestionID = selectedSuggestionID;
        this.suggestionStatus = suggestionStatus;
    }

    public void render() {
        SuggestionManager suggestionManager = SuggestionManager.getInstance();
        Suggestion suggestion = suggestionManager.getSuggestion(selectedSuggestionID);
        Options processSuggestionOptions = new ProcessSuggestionOptions(suggestion, suggestionStatus);

        // Display suggestion details
        processSuggestionOptions.display("Suggestion details: ");

        if (suggestionStatus == SuggestionStatus.PENDING) {
            // Allo process suggestion if suggestion is pending
            UI processSuggestionUI = new ProcessSuggestionUI(navigation, suggestion, selectedSuggestionID);
            processSuggestionUI.body();
        } else {
            // Let staff view details and go back only
            if (processSuggestionOptions.selection() == Dismiss.intOption()) {
                navigation.dismissView();
            }
        }
    }
}
