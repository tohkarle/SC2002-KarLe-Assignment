package cams.core.authentication.view;

import cams.Main;
import cams.core.authentication.controller.LogInViewController;
import cams.core.root.view.RootView;

public class LogInView {

    private String email ;
    private String password;
    private LogInViewController viewController;
    private RootView rootUI;

    public LogInView(RootView rootUI) {
        this.email = null;
        this.password  = null;
        this.viewController = new LogInViewController();
        this.rootUI = rootUI;
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
        // Set the currentUserID in rootUI after logging in user
        rootUI.setCurrentUserID(viewController.logIn(this.email, this.password));
    }
}
