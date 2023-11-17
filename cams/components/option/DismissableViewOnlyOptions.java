package cams.components.option;

import cams.interfaces.Displayable;
import cams.utils.Page;

public class DismissableViewOnlyOptions extends Options implements Displayable {

    @Override
    public void display(String title) {
        Page.header(title);
        super.printOptions();
    }
}
