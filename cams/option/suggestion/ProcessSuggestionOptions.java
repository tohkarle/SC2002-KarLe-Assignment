package cams.option.suggestion;

import java.util.Arrays;

import cams.model.Suggestion;
import cams.model.SuggestionStatus;
import cams.option.camp.CampInfoOptions;

public class ProcessSuggestionOptions extends CampInfoOptions {

    private Suggestion suggestion;
    private SuggestionStatus suggestionStatus;
    
    public ProcessSuggestionOptions(Suggestion suggestion, SuggestionStatus suggestionStatus) {
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
                "(1) Approve suggestion",
                "(2) Reject suggestion"
            ));
        }
    }    
}
