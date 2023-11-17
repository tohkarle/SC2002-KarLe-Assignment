package cams.view.user;

import cams.components.LoadingIndicator;
import cams.components.input.GetString;
import cams.interfaces.StringInput;
import cams.interfaces.View;
import cams.manager.UserManager;
import cams.utils.Dismiss;
import cams.utils.Page;
import cams.view.root.RootView;

public class ChangePasswordView implements View {

    private int userID;
    private UserManager manager;

    // Input fields
    private StringInput currentPasswordInput;
    private StringInput newPasswordInput;
    private StringInput reEnterNewPasswordInput;

    public ChangePasswordView(RootView rootView) {
        // Create the input fields
        this.currentPasswordInput = new GetString("Enter current password: ");
        this.newPasswordInput = new GetString("Enter new password: ");
        this.reEnterNewPasswordInput = new GetString("Re-enter new password: ");
        this.userID = rootView.getCurrentUserID();
        this.manager = rootView.getManager();
    }

    public void body() {

        String pass1 = "";
        String pass2 = "";
        String pass3 = "";

        Page.header("Change password");

        while (true) {
            // Check if current password is correct
            pass1 = this.currentPasswordInput.getValidString();
            if (pass1.equals(Dismiss.stringOption())) { return; }
            if (!manager.passwordIsCorrect(userID, pass1)) {
                System.out.println("Password incorrect, please try again.");
                continue;
            }

            // Enter and re-enter new password
            while (true) {
                pass2 = this.newPasswordInput.getValidString();
                if (pass2.equals(Dismiss.stringOption())) { return; }

                pass3 = this.reEnterNewPasswordInput.getValidString();
                if (pass3.equals(Dismiss.stringOption())) { return; }

                if (pass2.equals(pass3)) { break; }
                System.out.println("Passwords do not match, please try again.");
            }

            // Update password
            this.manager.setPassword(this.userID, pass3);
            LoadingIndicator.updateLoadingIndicator("password");
            return;
        }
    }
}
