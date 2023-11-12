package cams.core.authentication.view;

import cams.Main;
import cams.component.LoadingIndicator;
import cams.component.SelectionInput;
import cams.core.root.view.RootView;

public class LogInView {

    private String email ;
    private String password;
    private RootView rootView;

    public LogInView(RootView rootView) {
        this.email = null;
        this.password  = null;
        this.rootView = rootView;
    }

    public void show() {

        SelectionInput.pageHeader("Log in");

        // get userID
        System.out.print("Enter email: ");
        this.email = Main.scanner.nextLine();
        if (this.email.equals(SelectionInput.backOptionString())) { return; }

        // get password
        System.out.print("Enter password: ");
        this.password = Main.scanner.nextLine();
        if (this.password.equals(SelectionInput.backOptionString())) { return; }

        // Log in user
        this.logIn();
    }

    public void logIn() {
        if (!Main.authManager.isValidUser(this.email)) {
            System.out.println("Email not found!");
            return;
        }

        if (Main.authManager.checkPassword(this.email, this.password)){
            // password accepted
            // Set the currentUserID in rootUI after logging in user
            this.rootView.setCurrentUserID(Main.authManager.getUserID(this.email));
            LoadingIndicator.logInLoadingIndicator();
        } else {
            // password wrong, rejected
            System.out.println("Wrong password, please try again.");
        }
    }
}
