package cams.components.input;

import cams.Main;
import cams.utils.Dismiss;

public class GetSelectionWithDismiss extends GetOptionInput {

    public GetSelectionWithDismiss(int min, int max) {
        super.setMinMax(min, max);
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
