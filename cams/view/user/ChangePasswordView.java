package cams.view.user;

import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.ui.auth.ChangePasswordUI;

/**
 * View object for Change Password page
 */
public class ChangePasswordView implements View {

    private Navigation navigation;
    private Input getInput;

    /**
     * Initialize the ChangePasswordView
     * @param navigation Navigation object used to control navigation of the application
     * @param getInput Input object used to get input from user
     */
    public ChangePasswordView(Navigation navigation, Input getInput) {
        this.navigation = navigation;
        this.getInput = getInput;
    }

    /**
     * Render the ChangePasswordView
     */
    public void render() {
        UI changPasswordUI = new ChangePasswordUI(navigation, getInput);
        changPasswordUI.body();
    }
}
