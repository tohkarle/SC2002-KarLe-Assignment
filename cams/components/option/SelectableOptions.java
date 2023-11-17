package cams.components.option;

import cams.interfaces.DisplayableSelectable;
import cams.interfaces.IntInput;
import cams.ui.GetSelectionUI;

public class SelectableOptions extends Options implements DisplayableSelectable {
    
    @Override
    public void display(String title) {
        System.out.println("\n" + title);
        super.printOptions();
    }

    @Override
    public int selection() {
        IntInput selection = new GetSelectionUI(1, super.getOptionsSize());
        return selection.getValidInt();
    }
}
