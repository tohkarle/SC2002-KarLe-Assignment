package cams.ui;

import cams.Main;
import cams.components.input.GetOption;

public class GetSelectionUI extends GetOption {

    public GetSelectionUI(int min, int max) {
        super(min, max);
    }

    @Override
    public int getValidInt(String title) {
        System.out.println("");
        while (true) {
            System.out.print("Your selection: ");
            super.input = Main.scanner.nextLine();
            if (super.inputIsEmpty() || super.inputIsNotInt() || super.inputIsNotBetweenMinMax()) { continue; }
            return super.number;
        }
    }
}
