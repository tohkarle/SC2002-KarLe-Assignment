package cams.component;

import cams.Main;
import cams.util.Dismiss;

public class YourSelectionInputWithDismiss extends IntInputField {

    public YourSelectionInputWithDismiss(int min, int max) {
        super(min, max);
    }

    public int getValidInput() {
        System.out.println("");
        while (true) {
            System.out.print("Your selection: ");
            super.input = Main.scanner.nextLine();
            if (super.inputIsEmpty() || super.inputIsNotInt()) { continue; }
            if (super.option == Dismiss.intOption()) { return super.option; }
            if (super.inputIsNotBetweenMinMax()) { continue; }
            return super.option;
        }
    }
}
