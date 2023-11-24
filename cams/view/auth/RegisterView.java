package cams.view.auth;

import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.ui.auth.GetRegisterInfoUI;
import cams.utils.Page;

/**
 * View object for Register page
 */
public class RegisterView implements View {

    private Navigation navigation;
    private boolean isStaff;

    /**
     * Initialize the RegisterView
     * @param navigation Navigation object used to control navigation of the application
     * @param isStaff boolean of whether the user is a staff member
     */
    public RegisterView(Navigation navigation, boolean isStaff) {
        this.navigation = navigation;
        this.isStaff = isStaff;
    }

    /**
     * Render the RegisterView
     */
    public void render() {
        // Enter user info
        Page.header("Please enter your email, name, faculty and password."); 
        UI getRegisterInfoUI = new GetRegisterInfoUI(navigation, isStaff);
        getRegisterInfoUI.body();
    }
}
