package cams.option.suggestion;

import java.util.Arrays;

import cams.components.input.GetSelectionWithDismiss;
import cams.interfaces.IntInput;
import cams.model.Suggestion;
import cams.option.camp.CampInfoOptions;
import cams.utils.Page;

public class EditSuggestionOptions extends CampInfoOptions {

    private Suggestion suggestion;
    
    public EditSuggestionOptions(Suggestion suggestion) {
        super(suggestion.getCamp());
        this.suggestion = suggestion;
        this.changeOption();
    }

    public void changeOption() {
        super.getOptions().add(0, String.format("Title: %s", suggestion.getTitle()));
        super.getOptions().remove(String.format("Staff-in-charge: %s", super.getCamp().getStaffInCharge()));
        super.getOptions().addAll(Arrays.asList(
            "Update changes"
        ));
    }

    @Override
    public void display(String title) {
        Page.header(title);
        super.printOptions();
    }

    @Override
    public int selection() {
        IntInput selectionWithDismiss = new GetSelectionWithDismiss(1, super.getOptionsSize());
        return selectionWithDismiss.getValidInt("Your selection: ");
    }
}
