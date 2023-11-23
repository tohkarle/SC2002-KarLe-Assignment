package cams.components.option;

import cams.components.input.GetSelection;
import cams.interfaces.Displayable;
import cams.interfaces.IntInput;
import cams.utils.Page;


/**
 * Option object that presents a list only for viewing
 */
public class ViewOnlyOptions extends Options implements Displayable {


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
        IntInput selection = new GetSelection(-1, -1);
        return selection.getValidInt("Your selection: ");
    }
}
