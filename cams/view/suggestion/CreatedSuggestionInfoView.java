package cams.view.suggestion;

import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.manager.SuggestionManager;
import cams.model.Suggestion;
import cams.model.SuggestionStatus;
import cams.option.suggestion.CreatedSuggestionInfoOptions;
import cams.ui.suggestion.DeleteSuggestionUI;
import cams.utils.Dismiss;

public class CreatedSuggestionInfoView implements View {

    private Navigation navigation;
    private Input getInput;
    private int selectedSuggestionID;
    private SuggestionStatus suggestionStatus;

    public CreatedSuggestionInfoView(Navigation navigation, Input getInput, int selectedSuggestionID, SuggestionStatus suggestionStatus) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.selectedSuggestionID = selectedSuggestionID;
        this.suggestionStatus = suggestionStatus;
    }

    public void render() {

        SuggestionManager suggestionManager = SuggestionManager.getInstance();
        Suggestion suggestion = suggestionManager.getSuggestion(selectedSuggestionID);
        Options createdSuggestionOptions = new CreatedSuggestionInfoOptions(suggestion, suggestionStatus);

        // Display created suggestion details
        createdSuggestionOptions.display("Suggestion details: ");

        // Allow student to go back or edit suggestion and delete suggestion
        int option = createdSuggestionOptions.selection();
        if (option == Dismiss.intOption() ) { 
            navigation.dismissView();
            return; 
        }

        switch (option) {
            case 1:
                navigation.navigateTo(new EditSuggestionView(navigation, getInput, suggestion));
                break;
            case 2:
                UI deleteSuggestionUI = new DeleteSuggestionUI(navigation, getInput, suggestionManager, selectedSuggestionID);
                deleteSuggestionUI.body();
                break;
        }
    }
}
