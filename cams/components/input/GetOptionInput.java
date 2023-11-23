package cams.components.input;

import cams.utils.Dismiss;


/**
 * An abstract input object to get a valid input for a given range
 */
public abstract class GetOptionInput extends GetIntInput {
    
    // Input must be between and including min and max
    // Input must be an int
    // Input must not be empty

    private int min = Dismiss.intOption();
    private int max = Dismiss.intOption();


    /**
     * Abstract method to get a valid int option input
     * @param title The title of what the input is for
     * @return int of the option selected
     */
    @Override
    public abstract int getValidInt(String title);


    /**
     * A method to check if the input is within the specified range
     * @return boolean of whether the input is in range
     */
    public boolean inputIsNotBetweenMinMax() {
        if (super.number >= min && super.number <= max) { return false; }
        super.invalidInput();
        return true;
    }


    /**
     * A method to set the range of valid options for this input object
     * @param min The inclusive lower bound for the range of options
     * @param max The inclusive upper bound for the range of options
     */
    public void setMinMax(int min, int max) {
        this.min = min;
        this.max = max;
    }
}
