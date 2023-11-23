package cams.components.input;

import cams.Main;
import cams.utils.Dismiss;


/**
 * Input object to get input when there are 2 options
 */
public class ChooseBetweenTwoOptions extends GetOptionInput {

    private String option1;
    private String option2;

    /**
     * Initialize the objects options
     * @param option1 The first option title
     * @param option2 The second option title
     */
    public ChooseBetweenTwoOptions(String option1, String option2) {
        super.setMinMax(1, 2);
        this.option1 = option1;
        this.option2 = option2;
    }

    /**
     * Gets user input to choose between 2 options
     * @param title The title of the choice being presented
     * @return int of the option selected
     */
    @Override
    public int getValidInt(String title) {
        while (true) {
            System.out.print(title + " (1) " + this.option1 + " (2) " + this.option2 + ": ");
            super.input = Main.scanner.nextLine();
            if (super.inputIsEmpty() || super.inputIsNotInt()) { continue; }
            if (super.number == Dismiss.intOption()) { return super.number; }
            if (super.inputIsNotBetweenMinMax()) { continue; }
            return super.number;
        }
    }
}
