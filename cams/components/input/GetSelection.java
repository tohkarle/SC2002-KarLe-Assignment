package cams.components.input;

import cams.Main;

public class GetSelection extends GetOptionInput {

    public GetSelection(int min, int max) {
        super.setMinMax(min, max);
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
