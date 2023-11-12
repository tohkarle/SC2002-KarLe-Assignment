package cams.component;

import cams.Main;

public class YourSelectionWithBack extends IntInputField {

    public YourSelectionWithBack(int min, int max) {
        super(min, max);
    }

    public int getValidInput() {
        System.out.println("");
        while (true) {
            System.out.print("Your selection: ");
            super.input = Main.scanner.nextLine();
            if (super.inputIsEmpty() || super.inputIsNotInt()) { continue; }
            if (super.option == SelectionInput.backOptionInt()) { return super.option; }
            if (super.inputIsNotBetweenMinMax()) { continue; }
            return super.option;
        }
    }
}
