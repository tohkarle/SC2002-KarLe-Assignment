package cams.ui;

import cams.Main;
import cams.components.input.GetOption;

public class GetSelectionUI extends GetOption {

    public GetSelectionUI(int min, int max) {
        super("Your selection: ", min, max);
    }

    public int getValidInt() {
        System.out.println("");
        while (true) {
            System.out.print(super.title);
            super.input = Main.scanner.nextLine();
            if (super.inputIsEmpty() || super.inputIsNotInt() || super.inputIsNotBetweenMinMax()) { continue; }
            return super.number;
        }
    }
}
