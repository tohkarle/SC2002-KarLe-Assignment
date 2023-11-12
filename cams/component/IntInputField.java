package cams.component;

import cams.util.Dismiss;

public abstract class IntInputField implements IntInput {
    // Input must be between and including min and max
    // Input must be an int
    // Input must not be empty

    protected int min;
    protected int max;
    protected String input;
    protected int option;

    public IntInputField(int min, int max) {
        this.min = min;
        this.max = max;
        this.input = null;
        this.option = Dismiss.intOption();
    }

    public abstract int getValidInput();

    public boolean inputIsEmpty() {
        if (!this.input.isEmpty()) { return false; }
        System.out.println("You didn't enter anything. Please try again."); 
        return true;
    }

    public boolean inputIsNotInt() {
        try { 
            this.option = Integer.parseInt(input); 
            return false;
        } catch (NumberFormatException e) {
            this.invalidInput();
            return true;
        }
    }

    public boolean inputIsNotBetweenMinMax() {
        if (this.option >= this.min && this.option <= this.max) { return false; }
        this.invalidInput();
        return true;
    }

    public void invalidInput() {
        System.out.println("Invalid input, please try again.");
    }
}
