package cams.components.option;

import cams.interfaces.Displayable;

public class ViewOnlyOptions extends Options implements Displayable {

    @Override
    public void display(String title) {
        System.out.println("\n" + title);
        super.printOptions();
    }
}
