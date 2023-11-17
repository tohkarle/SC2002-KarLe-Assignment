package cams.components.option;

import java.util.ArrayList;

public abstract class Options {

    private ArrayList<String> options;

    public void printOptions() {
        for (int i = 0; i < this.options.size(); i++) {
            System.out.println("(" + (i + 1) + ") " + options.get(i));
        }
    }

    public abstract void display(String title);
    public abstract int selection();

    public ArrayList<String> getOptions() {
        return options;
    }

    public String getOption(int index) {
        return options.get(index);
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public void addOption(String option) {
        this.options.add(option);
    }

    public void replaceOption(String option, String newOption) {
        options.set(options.indexOf(option), newOption);
    }

    public int getOptionsSize() {
        return options.size();
    }
}
