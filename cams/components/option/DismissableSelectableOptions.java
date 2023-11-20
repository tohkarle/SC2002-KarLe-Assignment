package cams.components.option;

import cams.components.input.GetSelectionWithDismiss;
import cams.interfaces.DisplayableSelectable;
import cams.interfaces.IntInput;
import cams.utils.Page;

public class DismissableSelectableOptions extends Options implements DisplayableSelectable {

    @Override
    public void display(String title) {
        Page.header(title);
        super.printOptions();
    }

    @Override
    public int selection() {
        IntInput selectionWithDismiss = new GetSelectionWithDismiss(1, super.getOptionsSize());
        return selectionWithDismiss.getValidInt("Your selection: ");
    }
}
