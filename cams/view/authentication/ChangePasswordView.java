package cams.view.authentication;

import cams.component.LoadingIndicator;
import cams.component.StringInput;
import cams.component.StringInputField;
import cams.component.View;
import cams.manager.UserManager;
import cams.util.Dismiss;
import cams.util.Page;
import cams.view.root.RootView;

public class ChangePasswordView implements View {

    // Input field
    private StringInput currentPasswordInput;
    private StringInput newPasswordInput;
    private StringInput reEnterNewPasswordInput;
    private int userID;
    private UserManager manager;

    public ChangePasswordView(RootView rootView) {
        this.currentPasswordInput = new StringInputField("Enter current password: ");
        this.newPasswordInput = new StringInputField("Enter new password: ");
        this.reEnterNewPasswordInput = new StringInputField("Re-enter new password: ");
        this.userID = rootView.getCurrentUserID();
        this.manager = rootView.getManager();
    }

    public void show() {

        String pass1 = "";
        String pass2 = "";
        String pass3 = "";

        Page.header("Change password");

        while (true) {
            // Check if current password is correct
            pass1 = this.currentPasswordInput.getValidInput();
            if (pass1.equals(Dismiss.stringOption())) { return; }
            if (!manager.passwordIsCorrect(userID, pass1)) {
                System.out.println("Password incorrect, please try again.");
                continue;
            }

            // Enter and re-enter new password
            while (true) {
                pass2 = this.newPasswordInput.getValidInput();
                if (pass2.equals(Dismiss.stringOption())) { return; }

                pass3 = this.reEnterNewPasswordInput.getValidInput();
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
