package cams.core.authentication.view;

import cams.Main;
import cams.core.authentication.controller.LogInUIController;
import cams.core.root.view.RootUI;

public class LogInUI {

    private String name ;
    private String password;
    private LogInUIController logInUIController;
    private RootUI rootUI;

    public LogInUI(RootUI rootUI) {
        this.name = null;
        this.password  = null;
        this.logInUIController = new LogInUIController();
        this.rootUI = rootUI;
    }

    public void showUI() {
        System.out.println("\nLog in as a Staff or a Student");

        // get userID
        System.out.print("Enter name: ");
        this.name = Main.scanner.nextLine().toUpperCase().trim();

        // get password
        System.out.print("Enter password: ");
        this.password = Main.scanner.nextLine();

        // Log in user
        // Set the currentUserID and currentUserName in rootUI after logging in user
        rootUI.setCurrentUserID(logInUIController.logIn(this.name, this.password));
        rootUI.setCurrentUserName(this.name);
    }
}
