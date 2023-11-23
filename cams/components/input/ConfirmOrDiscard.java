package cams.components.input;


/**
 * Input object to get confirmation
 */
public class ConfirmOrDiscard extends ChooseBetweenTwoOptions {

    /**
     * Initialize the input object
     */
    public ConfirmOrDiscard() {
        super("Confirm", "Discard and go back");
    }


    /**
     * Gets the user to confirm or go back
     * @param title The title of what the confirmation is for
     * @return int of the choice, 1 for confirm, 2 for go back
     */
    @Override
    public int getValidInt(String title) {
        return super.getValidInt(title);
    }
}
