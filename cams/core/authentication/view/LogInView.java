package cams.core.authentication.view;

import cams.Main;
import cams.core.authentication.controller.LogInUIController;
import cams.core.root.view.RootView;

public class LogInView {

    private String email ;
    private String password;
    private LogInUIController logInUIController;
    private RootView rootUI;

    public LogInView(RootView rootUI) {
        this.email = null;
        this.password  = null;
        this.logInUIController = new LogInUIController();
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
        rootUI.setCurrentUserID(logInUIController.logIn(this.email, this.password));
    }
}
