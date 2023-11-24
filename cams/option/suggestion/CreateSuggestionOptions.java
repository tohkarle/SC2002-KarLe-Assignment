package cams.option.suggestion;

import java.util.Arrays;

import cams.components.input.GetSelectionWithDismiss;
import cams.interfaces.IntInput;
import cams.model.Camp;
import cams.option.camp.CampInfoOptions;
import cams.utils.Page;

/**
 * Options object for submitting a suggestion in the CreateSuggestionView
 */
public class CreateSuggestionOptions extends CampInfoOptions {

    /**
     * Initialize the CreateSuggestion options
     * @param camp The camp to submit a suggestion for
     */
    public CreateSuggestionOptions(Camp camp) {
        super(camp);
        this.changeOption();
    }

    /**
     * Change the options to add the submit suggestion option
     */
    public void changeOption() {
        super.getOptions().remove(String.format("Staff-in-charge: %s", super.getCamp().getStaffInCharge()));
        super.getOptions().addAll(Arrays.asList(
            "Submit suggestion"
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
