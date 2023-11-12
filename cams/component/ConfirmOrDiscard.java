package cams.component;

import cams.Main;

public class ConfirmOrDiscard extends IntInputField {

    private String title;

    public ConfirmOrDiscard(String title) {
        super(1, 2);
        this.title = title;
    }

    public int getValidInput() {
        while (true) {
            System.out.print("Confirm " + title + "? (1) Confirm (2) Discard and go back: ");
            super.input = Main.scanner.nextLine();
            if (super.inputIsEmpty() || super.inputIsNotInt()) { continue; }
            if (super.option == SelectionInput.backOptionInt()) { return super.option; }
            if (super.inputIsNotBetweenMinMax()) { continue; }
            return super.option;
        }
    }
}
