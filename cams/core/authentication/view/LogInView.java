package cams.core.authentication.view;

import cams.Main;
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
        System.out.println("Log in");

        // get userID
        System.out.print("Enter email: ");
        this.email = Main.scanner.nextLine();

        // get password
        System.out.print("Enter password: ");
        this.password = Main.scanner.nextLine();

        // Log in user
        this.logIn();
    }

    public void logIn() {
        if (!Main.authManager.isValidUser(this.email)) {
            System.out.println("Email not found!\n");
            return;
        }

        if (Main.authManager.checkPassword(this.email, this.password)){
            // password accepted
            // Set the currentUserID in rootUI after logging in user
            this.rootView.setCurrentUserID(Main.authManager.getUserID(this.email));
            return;

        } else {
            // password wrong, rejected
            System.out.println("Wrong password, please try again.\n");
            return;
        }
    }
}
