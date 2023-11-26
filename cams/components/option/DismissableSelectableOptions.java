package cams.components.option;

import cams.components.input.GetSelectionWithDismiss;
import cams.interfaces.Displayable;
import cams.interfaces.IntInput;
import cams.interfaces.Selectable;
import cams.utils.Page;


/**
 * Option object that presents options that can be backed out/ dismissed
 */
public class DismissableSelectableOptions extends Options implements Displayable, Selectable {

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
