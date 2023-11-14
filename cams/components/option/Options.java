package cams.components.option;

import java.util.ArrayList;

import cams.interfaces.IntInput;
import cams.ui.GetSelectionUI;
import cams.ui.GetSelectionWithDismissUI;

public class Options {

    protected ArrayList<String> options;

    public void display(String title) {
        System.out.println("\n" + title);
        for (int i = 0; i < this.options.size(); i++) {
            System.out.println("(" + (i + 1) + ") " + options.get(i));
        }
    }

    public void viewOnly(String title) {
        System.out.println("\n" + title);
        for (int i = 0; i < this.options.size(); i++) {
            System.out.println(this.options.get(i));
        }
    }

    public int selection() {
        IntInput selection = new GetSelectionUI(1, this.options.size());
        return selection.getValidInt();
    }

    public int selectionWithDismiss() {
        IntInput selectionWithDismiss = new GetSelectionWithDismissUI(1, this.options.size());
        return selectionWithDismiss.getValidInt();
    }

    public int getOptionsSize() {
        return options.size();
    }

    public String getOption(int index) {
        return options.get(index);
    }

    public void setOption(String option, String newOption) {
        options.set(options.indexOf(option), newOption);
    }
}
