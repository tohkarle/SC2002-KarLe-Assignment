package cams.interfaces;


/**
 * Interface to ensure input object promts user to input
 * a valid int
 */
public interface IntInput {

    /**
     * A method to get a valid int input
     * @param title The title of what the input is for
     * @return int
     */
    public int getValidInt(String title);
}
