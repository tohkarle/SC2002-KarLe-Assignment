package cams.option.suggestion;

import java.util.Arrays;

import cams.components.input.GetSelection;
import cams.components.input.GetSelectionWithDismiss;
import cams.interfaces.IntInput;
import cams.model.Suggestion;
import cams.model.SuggestionStatus;
import cams.option.camp.CampInfoOptions;

public class CreatedSuggestionOptions extends CampInfoOptions {
    
    private Suggestion suggestion;
    private SuggestionStatus suggestionStatus;

    public CreatedSuggestionOptions(Suggestion suggestion, SuggestionStatus suggestionStatus) {
        super(suggestion.getCamp());
        this.suggestion = suggestion;
        this.suggestionStatus = suggestionStatus;
        this.changeOption();
    }

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

    @Override
    public int selection() {
        IntInput selection = new GetSelectionWithDismiss(1, 2);
        return selection.getValidInt("Your selection: ");
    }
}
