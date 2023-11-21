package cams.components.input;

import cams.utils.Dismiss;

public abstract class GetOptionInput extends GetIntInput {
    
    // Input must be between and including min and max
    // Input must be an int
    // Input must not be empty

    private int min = Dismiss.intOption();
    private int max = Dismiss.intOption();

    @Override
    public abstract int getValidInt(String title);

    public boolean inputIsNotBetweenMinMax() {
        if (super.number >= min && super.number <= max) { return false; }
        super.invalidInput();
        return true;
    }

    public void setMinMax(int min, int max) {
        this.min = min;
        this.max = max;
    }
}
