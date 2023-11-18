package cams.ui;

import cams.Main;
import cams.components.input.GetOptionInput;
import cams.utils.Dismiss;

public class GetSelectionWithDismissUI extends GetOptionInput {

    public GetSelectionWithDismissUI(int min, int max) {
        super(min, max);
    }

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
