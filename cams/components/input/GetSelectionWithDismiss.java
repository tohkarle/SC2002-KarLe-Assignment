package cams.components.input;

import cams.Main;
import cams.utils.Dismiss;


/**
 * Input object to get input for a given range with the
 * option to go back/ dismiss, not chhosing an option
 */
public class GetSelectionWithDismiss extends GetOptionInput {


    /**
     * Initialize the input object
     * @param min The inclusive lower bound for the range of options
     * @param max The inclusive upper bound for the range of options
     */
    public GetSelectionWithDismiss(int min, int max) {
        super.setMinMax(min, max);
    }


    /**
     * A method to get a valid int input for the specified range
     * @param title The title of what the input is for
     * @return int of the option selected, Dismiss.intOption() -> int for a dismiss
     */
    @Override
    public int getValidInt(String title) {
        System.out.println("");
        while (true) {
            System.out.print(title);
            super.input = Main.scanner.nextLine();
            if (super.inputIsEmpty() || super.inputIsNotInt()) { continue; }
            if (super.number == Dismiss.intOption()) { return super.number; }
            if (super.inputIsNotBetweenMinMax()) { continue; }
            return super.number;
        }
    }
}
