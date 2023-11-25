package cams.option.suggestion;

import java.util.Arrays;

import cams.components.input.GetSelectionWithDismiss;
import cams.interfaces.IntInput;
import cams.model.Suggestion;
import cams.option.camp.CampInfoOptions;
import cams.utils.Page;

/**
 * Options object for managing edits to a suggestion in the EditSuggestionView
 */
public class EditSuggestionOptions extends CampInfoOptions {

    private Suggestion suggestion;
    
    /**
     * Initialize the EditSuggestion options
     * @param suggestion The suggestion to edit
     */
    public EditSuggestionOptions(Suggestion suggestion) {
        super(suggestion.getCamp());
        this.suggestion = suggestion;
        this.changeOption();
    }

    /**
     * Change the options to add the update changes option
     */
    public void changeOption() {
        super.getOptions().add(0, String.format("Title: %s", suggestion.getTitle()));
        super.getOptions().remove(String.format("Staff-in-charge: %s", super.getCamp().getStaffInCharge()));
        super.getOptions().addAll(Arrays.asList(
            "Update changes"
        ));
    }


    /**
     * The method to display the options
     * @param title The title of what the options are about
     */
    @Override
    public void display(String title) {
        Page.header(title);
        super.printOptions();
    }


    /**
     * A method to get the user to choose from the presented options
     * @return int of the option the user selected
     */
    @Override
    public int selection() {
        IntInput selectionWithDismiss = new GetSelectionWithDismiss(1, super.getOptionsSize());
        return selectionWithDismiss.getValidInt("Your selection: ");
    }
}
