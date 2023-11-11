package cams.core.authentication.view;

import cams.Main;
import cams.core.authentication.controller.LogInUIController;
import cams.core.root.view.RootView;

public class LogInView {

    private String name ;
    private String password;
    private LogInUIController logInUIController;
    private RootView rootUI;

    public LogInView(RootView rootUI) {
        this.name = null;
        this.password  = null;
        this.logInUIController = new LogInUIController();
        this.rootUI = rootUI;
    }

    public void show() {
        System.out.println("Log in");

        // get userID
        System.out.print("Enter name: ");
        this.name = Main.scanner.nextLine();

        // get password
        System.out.print("Enter password: ");
        this.password = Main.scanner.nextLine();

        // Log in user
        // Set the currentUserID in rootUI after logging in user
        rootUI.setCurrentUserID(logInUIController.logIn(this.name, this.password));
    }
}
