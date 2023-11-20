package cams.view.suggestion;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.model.SuggestionStatus;
import cams.option.suggestion.CampSuggestionOptions;
import cams.utils.Dismiss;

public class CampSuggestionsView implements View {

    private Navigation navigation;
    private SuggestionStatus suggestionStatus;
    private int selectedCampID;

    public CampSuggestionsView(Navigation navigation, SuggestionStatus suggestionStatus, int selectedCampID) {
        this.navigation = navigation;
        this.suggestionStatus = suggestionStatus;
        this.selectedCampID = selectedCampID;
    }

    public void render() {

        // Display suggestion
        Options campSuggestionOptions = new CampSuggestionOptions(suggestionStatus, selectedCampID);
        campSuggestionOptions.display("Select suggestion to view details:");

        // Let user select suggestion to view details
        int selectedSuggestionID = campSuggestionOptions.selection();
        if (selectedSuggestionID == Dismiss.intOption()) { 
            navigation.dismissView();
            return; 
        }

        // Navigate to ProcessSuggestionView
        navigation.navigateTo(new ProcessSuggestionView(navigation, selectedSuggestionID, suggestionStatus));
    }
}
