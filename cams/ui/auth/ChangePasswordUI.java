package cams.ui.auth;

import cams.components.input.ConfirmOrDiscard;
import cams.components.input.GetStringInput;
import cams.interfaces.IntInput;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.UserManager;
import cams.utils.Dismiss;
import cams.utils.LoadingIndicator;
import cams.utils.Page;

public class ChangePasswordUI extends GetStringInput implements UI {

    private Navigation navigation;

    public ChangePasswordUI(Navigation navigation) {
        this.navigation = navigation;
    }
    
    public void body() {
        
        UserManager userManager = UserManager.getInstance();
        IntInput confirm = new ConfirmOrDiscard();

        String pass1 = "";
        String pass2 = "";
        String pass3 = "";

        Page.header("Change password");

        // Check if current password is correct
        pass1 = super.getValidString("Enter current password: ");
        if (pass1.equals(Dismiss.stringOption())) { 
            navigation.dismissView();
            return; 
        }
        if (!userManager.getCurrentUser().passwordMatches(pass1)) {
            System.out.println("Password incorrect, please try again.");
            return;
        }

        // Enter and re-enter new password
        while (true) {
            pass2 = super.getValidString("Enter new password: ");

            if (pass2.equals(Dismiss.stringOption())) { 
                navigation.dismissView();
                return; 
            }

            // Check if new password same as old password
            if (pass2.equals(pass1)) {
                System.out.println("New password cannot be the same as old password, please try again.");
                continue;
            }

            pass3 = super.getValidString("Re-enter new password: ");

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
        if (confirm.getValidInt("Confirm changes?") != 1) { return; };

        // Update password
        userManager.updatePassword(pass3);
        LoadingIndicator.updateLoadingIndicator("password");
        navigation.dismissView();
        return;
    }
}
