package cams.components.option;

import cams.components.input.GetSelection;
import cams.interfaces.DisplayableSelectable;
import cams.interfaces.IntInput;

public class SelectableOptions extends Options implements DisplayableSelectable {
    
    @Override
    public void display(String title) {
        System.out.println("\n" + title);
        super.printOptions();
    }

    @Override
    public int selection() {
        IntInput selection = new GetSelection(1, super.getOptionsSize());
        return selection.getValidInt("Your selection: ");
    }
}
