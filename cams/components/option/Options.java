package cams.components.option;

import java.util.ArrayList;


/**
 * A base abstracct class to create Option objects
 */
public abstract class Options {

    /**
     * List of string for each available option
     */
    private ArrayList<String> options;

    /**
     * The method to display the options
     * @param title The title of what the options are about
     */
    public abstract void display(String title);


    /**
     * A method to get the user to choose from the presented options
     * @return int of the option the user selected
     */
    public abstract int selection();


    /**
     * A method to print all the loaded options
     */
    public void printOptions() {
        for (int i = 0; i < this.options.size(); i++) {
            System.out.println("(" + (i + 1) + ") " + options.get(i));
        }
    }


    /**
     * A method to get all the option strings in the Option object
     * @return
     */
    public ArrayList<String> getOptions() {
        return options;
    }


    /**
     * A method to get a option string via index
     * @param index The index of the option string, 0-indexed
     * @return String
     */
    public String getOption(int index) {
        return options.get(index);
    }


    /**
     * A method to set the options to a new list
     * @param options ArrayList<String> object with the new option strings
     */
    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }
    

    /**
     * A method to add an option string
     * @param option String of the new option
     */
    public void addOption(String option) {
        this.options.add(option);
    }


    /**
     * A method to replace a option string via string matching
     * @param option The string of the old option string
     * @param newOption The string of the new option string
     */
    public void replaceOption(String option, String newOption) {
        options.set(options.indexOf(option), newOption);
    }


    /**
     * A method to get the number of options available
     * @return int of the number of options available
     */
    public int getOptionsSize() {
        return options.size();
    }
}
