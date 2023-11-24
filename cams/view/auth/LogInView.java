package cams.view.auth;

import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.ui.auth.GetEmailPasswordUI;
import cams.utils.Page;

/**
 * View object for Login page
 */
public class LogInView implements View {

    private Navigation navigation;

    /**
     * Initialize the LogInView
     * @param navigation Navigation object used to control navigation of the application
     */
    public LogInView(Navigation navigation) {
        this.navigation = navigation;
    }

    /**
     * Render the LogInView
     */
    public void render() {
        Page.header("Log in");

        // Get email and password
        UI getEmailPasswordUI = new GetEmailPasswordUI(navigation);
        getEmailPasswordUI.body();
    }
}
