package cams.option.camp;

import java.util.ArrayList;
import java.util.Arrays;

import cams.components.input.GetSelectionWithDismiss;
import cams.components.option.DismissableSelectableOptions;
import cams.interfaces.IntInput;
import cams.utils.Page;

/**
 * Options object for selecting camp filter in the FilterAllCampsView
 */
public class FilterAllCampsOptions extends DismissableSelectableOptions {

    /**
     * Initialize the camp filter options
     */
    public FilterAllCampsOptions() {
        super.setOptions( 
            new ArrayList<String>(Arrays.asList(
                "Remove filter",
                "Name",
                "Faculty",
                "Location",
                "Description",
                "Dates",
                "Registration closing date",
                "Total slots",
                "Total committee slots",
                "Staff-in-charge"
            ))
        );
    }


    /**
     * The method to display the options
     * @param title The title of what the options are about
     */
    @Override
    public void display(String title) {
        Page.header(title);
        for (int i = 0; i < super.getOptionsSize(); i++) {
            System.out.println("(" + (i) + ") " + super.getOption(i));
        }
    }


    /**
     * A method to get the user to choose from the presented options
     * @return int of the option the user selected
     */
    @Override
    public int selection() {
        IntInput selectionWithDismiss = new GetSelectionWithDismiss(0, super.getOptionsSize() - 1);
        int option = selectionWithDismiss.getValidInt("Your selection: ");
        return option;
    }
}
