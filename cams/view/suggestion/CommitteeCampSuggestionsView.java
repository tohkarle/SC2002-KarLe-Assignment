package cams.view.suggestion;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.model.SuggestionStatus;
import cams.option.suggestion.CommitteeCampSuggestionsOptions;
import cams.utils.Dismiss;

public class CommitteeCampSuggestionsView implements View {

    private Navigation navigation;
    private SuggestionStatus suggestionStatus;
    private int selectedCampID;


    public CommitteeCampSuggestionsView(Navigation navigation, SuggestionStatus suggestionStatus, int selectedCampID) {
        this.navigation = navigation;
        this.suggestionStatus = suggestionStatus;
        this.selectedCampID = selectedCampID;
    }

    public void render() {

        // Display suggestions
        Options committeeCampSuggestionsOptions = new CommitteeCampSuggestionsOptions(suggestionStatus, selectedCampID);
        committeeCampSuggestionsOptions.display("Select suggestion to view details:");

        // Let user select suggestion to view details
        int selectedSuggestionID = committeeCampSuggestionsOptions.selection();
        if (selectedSuggestionID == Dismiss.intOption()) { 
            navigation.dismissView();
            return; 
        }

        // Navigate to CreatedSuggestionInfoView
        navigation.navigateTo(new CreatedSuggestionInfoView(navigation, selectedSuggestionID, suggestionStatus));
    }
}