package cams.components.option;

import cams.components.input.GetSelection;
import cams.interfaces.Displayable;
import cams.interfaces.IntInput;
import cams.interfaces.Selectable;


/**
 * Option object that presents a list for selection
 */
public class SelectableOptions extends Options implements Displayable, Selectable {

    /**
     * The method to display the options
     * @param title The title of what the options are about
     */
    @Override
    public void display(String title) {
        System.out.println("\n" + title);
        super.printOptions();
    }


    /**
     * A method to get the user to choose from the presented options
     * @return int of the option the user selected
     */
    @Override
    public int selection() {
        IntInput selection = new GetSelection(1, super.getOptionsSize());
        return selection.getValidInt("Your selection: ");
    }
}
