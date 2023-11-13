package cams.components.input;

public abstract class GetOption extends GetInt {
    
    // Input must be between and including min and max
    // Input must be an int
    // Input must not be empty

    protected int min;
    protected int max;

    public GetOption(String title, int min, int max) {
        super(title);
        this.min = min;
        this.max = max;
    }

    @Override
    public abstract int getValidInt();

    public boolean inputIsNotBetweenMinMax() {
        if (super.number >= min && super.number <= max) { return false; }
        super.invalidInput();
        return true;
    }
}
