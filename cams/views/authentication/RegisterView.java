package cams.views.authentication;

import cams.components.input.GetString;
import cams.interfaces.IntInput;
import cams.interfaces.StringInput;
import cams.interfaces.View;
import cams.ui.GetSelectionWithDismissUI;
import cams.utils.AuthUtil;
import cams.utils.Dismiss;
import cams.utils.Page;

public class RegisterView implements View {
    
    private int option;
    private AuthUtil authenticate;

    // Input fields
    private StringInput emailInput;
    private StringInput nameInput;
    private StringInput facultyInput;
    private StringInput passwordInput;

    public RegisterView(AuthUtil authenticate) {
        this.option = -1;
        this.authenticate = authenticate;

        // Create input fields
        this.emailInput = new GetString("Enter email: ");
        this.nameInput = new GetString("Enter name: ");
        this.facultyInput = new GetString("Enter faculty: ");
        this.passwordInput = new GetString("Enter password: ");
    }

    public void body() {

        chooseStaffOrStudent();

        // Go back to previous page if user enters -1
        if (this.option == Dismiss.intOption()) { return; }

        regiatrationFields();
    }

    public void chooseStaffOrStudent() {
        Page.header("Do you want to register as a Staff or a Student?");
        System.out.println("(1) Staff");
        System.out.println("(2) Student");

        IntInput selectionWithDismiss = new GetSelectionWithDismissUI(1, 2);
        this.option = selectionWithDismiss.getValidInt();
    }

    public void regiatrationFields() {
        boolean isStaff = (this.option == 1);

        while (true) {
            Page.header("Please enter your email, name, password and faculty.");

            // get email
            String email = emailInput.getValidString();
            if (email.equals(Dismiss.stringOption())) { return; }

            // get name
            String name = nameInput.getValidString();
            if (name.equals(Dismiss.stringOption())) { return; }

            // get faculty
            String faculty = facultyInput.getValidString();
            if (faculty.equals(Dismiss.stringOption())) { return; }

            // get password
            String password = passwordInput.getValidString();
            if (password.equals(Dismiss.stringOption())) { return; }

            // Register user
            if (authenticate.registerSuccessful(email, name, password, faculty, isStaff)) { return; }
        }
    }
}
