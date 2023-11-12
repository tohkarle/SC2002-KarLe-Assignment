package cams.component;

import cams.Main;

public class YourSelection extends IntInputField {

    public YourSelection(int min, int max) {
        super(min, max);
    }

    public int getValidInput() {
        System.out.println("");
        while (true) {
            System.out.print("Your selection: ");
            super.input = Main.scanner.nextLine();
            if (super.inputIsEmpty() || super.inputIsNotInt() || super.inputIsNotBetweenMinMax()) { continue; }
            return super.option;
        }
    }
}
