package cams.views.authentication;

import cams.components.input.GetString;
import cams.interfaces.StringInput;
import cams.interfaces.View;
import cams.utils.AuthUtil;
import cams.utils.Dismiss;
import cams.utils.Page;

public class LogInView implements View {

    private AuthUtil authenticate;

    // Input fields
    private StringInput emailInput;
    private StringInput passwordInput;

    public LogInView(AuthUtil authenticate) {
        this.authenticate = authenticate;

        // Create input fields
        this.emailInput = new GetString("Enter email: ");
        this.passwordInput = new GetString("Enter password: ");
    }

    public void body() {
        while (true) {
            Page.header("Log in");

            // get userID
            String email = emailInput.getValidString();
            if (email.equals(Dismiss.stringOption())) { return; }

            // get password
            String password = passwordInput.getValidString();
            if (password.equals(Dismiss.stringOption())) { return; }

            // Log in
            if (authenticate.logInSuccessful(email, password)) { return; }
        }
    }
}
