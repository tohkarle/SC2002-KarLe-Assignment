package cams.interfaces;


/**
 * Interface to ensure the input object has all the required input needs
 */
public interface Input extends IntInput, StringInput, DateInput {

    /**
     * A method to promt user to input a confirmation
     * @param title What the input is for
     * @return int of decision
     */
    public int confirmOrDiscard(String title);
}
