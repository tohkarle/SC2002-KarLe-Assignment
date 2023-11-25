package cams.interfaces;


/**
 * Interface to make a new navigation object
 */
public interface Navigation {

    /**
     * A method to change the current view
     */
    public void navigateTo(View view);

    /**
     * A method to go back to the previous view layer
     */
    public void dismissView();

    /**
     * A method to go back to the root view
     */
    public void popToRoot();
}
