package cams.ui;

import cams.Main;
import cams.components.input.GetOption;
import cams.utils.Dismiss;

public class GetSelectionWithDismissUI extends GetOption {

    public GetSelectionWithDismissUI(int min, int max) {
        super("Your selection: ", min, max);
    }

    public int getValidInt() {
        System.out.println("");
        while (true) {
            System.out.print(super.title);
            super.input = Main.scanner.nextLine();
            if (super.inputIsEmpty() || super.inputIsNotInt()) { continue; }
            if (super.number == Dismiss.intOption()) { return super.number; }
            if (super.inputIsNotBetweenMinMax()) { continue; }
            return super.number;
        }
    }
}