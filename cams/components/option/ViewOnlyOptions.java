package cams.components.option;

import cams.interfaces.Displayable;
import cams.interfaces.IntInput;
import cams.ui.GetSelectionUI;
import cams.utils.Page;

public class ViewOnlyOptions extends Options implements Displayable {

    @Override
    public void display(String title) {
        Page.header(title);
        for (int i = 0; i < super.getOptionsSize(); i++) {
            System.out.println(super.getOption(i));
        }
    }

    @Override
    public int selection() {
        IntInput selection = new GetSelectionUI(-1, -1);
        return selection.getValidInt("Your selection: ");
    }
}
