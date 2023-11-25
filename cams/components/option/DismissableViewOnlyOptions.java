package cams.components.option;

import cams.components.input.GetSelectionWithDismiss;
import cams.interfaces.Displayable;
import cams.interfaces.IntInput;
import cams.utils.Page;


/**
 * Option object that presents a list that can only be backed out from
 */
public class DismissableViewOnlyOptions extends Options implements Displayable {


    /**
     * The method to display the options
     * @param title The title of what the options are about
     */
    @Override
    public void display(String title) {
        Page.header(title);
        for (int i = 0; i < super.getOptionsSize(); i++) {
            System.out.println(super.getOption(i));
        }
    }


    /**
     * A method to get the user to choose from the presented options
     * @return int of the option the user selected
     */
    @Override
    public int selection() {
        IntInput selectionWithDismiss = new GetSelectionWithDismiss(-1, -1);
        return selectionWithDismiss.getValidInt("Your selection: ");
    }
}
