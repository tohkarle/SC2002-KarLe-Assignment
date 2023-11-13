package cams.ui;

import cams.Main;
import cams.components.input.GetOption;
import cams.utils.Dismiss;

public class ChooseBetweenTwoOptionsUI extends GetOption {

    private String option1;
    private String option2;

    public ChooseBetweenTwoOptionsUI(String title, String option1, String option2) {
        super(title, 1, 2);
        this.option1 = option1;
        this.option2 = option2;
    }

    public int getValidInt() {
        while (true) {
            System.out.print(super.title + " (1) " + this.option1 + " (2) " + this.option2 + ": ");
            super.input = Main.scanner.nextLine();
            if (super.inputIsEmpty() || super.inputIsNotInt()) { continue; }
            if (super.number == Dismiss.intOption()) { return super.number; }
            if (super.inputIsNotBetweenMinMax()) { continue; }
            return super.number;
        }
    }
}
