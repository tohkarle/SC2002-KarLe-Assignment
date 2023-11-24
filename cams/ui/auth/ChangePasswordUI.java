package cams.ui.auth;

import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.UserManager;
import cams.utils.Dismiss;
import cams.utils.LoadingIndicator;
import cams.utils.Page;

/**
 * UI object for Change Password page
 */
public class ChangePasswordUI implements UI {

    private Navigation navigation;
    private Input getInput;

    /**
     * Initialize the ChangePasswordUI
     * @param navigation Navigation object used to control navigation of the application
     * @param getInput Input object used to get input from user
     */
    public ChangePasswordUI(Navigation navigation, Input getInput) {
        this.navigation = navigation;
        this.getInput = getInput;
    }
    
    /**
     * Executes user interaction logic for Change Password page
     */
    public void body() {
        
        UserManager userManager = UserManager.getInstance();

        String pass1 = "";
        String pass2 = "";
        String pass3 = "";

        Page.header("Change password");

        while (true) {
            // Check if current password is correct
            pass1 = getInput.getValidString("Enter current password: ");
            if (pass1.equals(Dismiss.stringOption())) { 
                navigation.dismissView();
                return; 
            }
            if (!userManager.getCurrentUser().passwordMatches(pass1)) {
                System.out.println("Password incorrect, please try again.");
                continue;
            }
            break;
        }

        // Enter and re-enter new password
        while (true) {
            pass2 = getInput.getValidString("Enter new password: ");

            if (pass2.equals(Dismiss.stringOption())) { 
                navigation.dismissView();
                return; 
            }

            // Check if new password same as old password
            if (pass2.equals(pass1)) {
                System.out.println("New password cannot be the same as old password, please try again.");
                continue;
            }

            pass3 = getInput.getValidString("Re-enter new password: ");

            if (pass3.equals(Dismiss.stringOption())) { 
                navigation.dismissView();
                return; 
            }

            if (pass2.equals(pass3)) { break; }

            if (!pass2.equals(pass3)) {
                System.out.println("Passwords do not match, please try again.");
            }
        }
        
        // Confirm changes
        if (getInput.confirmOrDiscard("Confirm changes?") != 1) { return; };

        // Update password
        userManager.updatePassword(pass3);
        LoadingIndicator.updateLoadingIndicator("password");
        navigation.dismissView();
        return;
    }
}
