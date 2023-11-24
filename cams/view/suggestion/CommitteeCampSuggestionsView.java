package cams.view.suggestion;

import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.model.SuggestionStatus;
import cams.option.suggestion.CommitteeCampSuggestionsOptions;
import cams.utils.Dismiss;

/**
 * View object for Committee Camp Suggestions page
 */
public class CommitteeCampSuggestionsView implements View {

    private Navigation navigation;
    private Input getInput;
    private SuggestionStatus suggestionStatus;
    private int selectedCampID;


    /**
     * Initialize the CommitteeCampSuggestionsView
     * @param navigation Navigation object used to control navigation of the application
     * @param getInput Input object used to get input from user
     * @param suggestionStatus The status of the suggestions to view
     * @param selectedCampID int of the selected camp ID
     */
    public CommitteeCampSuggestionsView(Navigation navigation, Input getInput, SuggestionStatus suggestionStatus, int selectedCampID) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.suggestionStatus = suggestionStatus;
        this.selectedCampID = selectedCampID;
    }

    /**
     * Render the CommitteeCampSuggestionsView
     */
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
        navigation.navigateTo(new CreatedSuggestionInfoView(navigation, getInput, selectedSuggestionID, suggestionStatus));
    }
}
