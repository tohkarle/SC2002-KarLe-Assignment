package cams.option.suggestion;

import java.util.Arrays;

import cams.components.input.GetSelectionWithDismiss;
import cams.interfaces.IntInput;
import cams.model.Suggestion;
import cams.model.SuggestionStatus;
import cams.option.camp.CampInfoOptions;

/**
 * Options object for selecting whether to edit or delete a suggestion in the CreatedSuggestionInfoView
 */
public class CreatedSuggestionInfoOptions extends CampInfoOptions {
    
    private Suggestion suggestion;
    private SuggestionStatus suggestionStatus;

    /**
     * Initialize the CreatedSuggestionInfo options
     * @param suggestion The suggestion to edit or delete
     * @param suggestionStatus The status of the suggestion
     */
    public CreatedSuggestionInfoOptions(Suggestion suggestion, SuggestionStatus suggestionStatus) {
        super(suggestion.getCamp());
        this.suggestion = suggestion;
        this.suggestionStatus = suggestionStatus;
        this.changeOption();
    }

    /**
     * Change the options to add the edit and delete suggestion options
     */
    public void changeOption() {
        super.getOptions().add(0, String.format("Title: %s", suggestion.getTitle()));
        super.getOptions().remove(String.format("Staff-in-charge: %s", super.getCamp().getStaffInCharge()));
        if (suggestionStatus == SuggestionStatus.PENDING) {
            super.getOptions().addAll(Arrays.asList(
                "(1) Edit suggestion",
                "(2) Delete suggestion"
            ));
        }
    }


    /**
     * A method to get the user to choose from the presented options
     * @return int of the option the user selected
     */
    @Override
    public int selection() {
        IntInput selection = new GetSelectionWithDismiss(1, 2);
        return selection.getValidInt("Your selection: ");
    }
}
