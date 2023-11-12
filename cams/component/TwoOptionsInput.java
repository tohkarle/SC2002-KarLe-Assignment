package cams.component;

import cams.Main;
import cams.util.Dismiss;

public class TwoOptionsInput extends IntInputField {

    protected String title;
    protected String option1;
    protected String option2;

    public TwoOptionsInput(String title, String option1, String option2) {
        super(1, 2);
        this.title = title;
        this.option1 = option1;
        this.option2 = option2;
    }

    public int getValidInput() {
        while (true) {
            System.out.print(this.title + " (1) " + this.option1 + " (2) " + this.option2 + ": ");
            super.input = Main.scanner.nextLine();
            if (super.inputIsEmpty() || super.inputIsNotInt()) { continue; }
            if (super.option == Dismiss.intOption()) { return super.option; }
            if (super.inputIsNotBetweenMinMax()) { continue; }
            return super.option;
        }
    }
}
