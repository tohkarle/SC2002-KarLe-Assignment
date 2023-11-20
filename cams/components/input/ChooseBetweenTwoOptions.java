package cams.components.input;

import cams.Main;
import cams.utils.Dismiss;

public class ChooseBetweenTwoOptions extends GetOptionInput {

    private String option1;
    private String option2;

    public ChooseBetweenTwoOptions(String option1, String option2) {
        super(1, 2);
        this.option1 = option1;
        this.option2 = option2;
    }

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
