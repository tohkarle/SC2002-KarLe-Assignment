package cams.interfaces;


/**
 * Interface to ensure the input object can prompt user
 * for a valid string input
 */
public interface StringInput {

    /**
     * A method to get a valid string input
     * @param title The title of what the input is for
     * @return String
     */
    public String getValidString(String title);
}
