package cams.core.authentication.view;

import cams.Main;
import cams.core.authentication.controller.LogInUIController;

public class LogInUI {

    private String userID ;
    private String password;
    private LogInUIController logInUIController;

    public LogInUI() {
        this.userID = "";
        this.password  = "";
        this.logInUIController = new LogInUIController();
    }

    public void showUI() {
        System.out.println("\nWelcome to the Camp Application and Management System");

        // get userID
        System.out.print("Enter userID: ");
        this.userID = Main.scanner.nextLine().toUpperCase().trim();

        // get password
        System.out.print("Enter password: ");
        this.password = Main.scanner.nextLine();

        // Log in user
        logInUIController.logIn(this.userID, this.password);
    }
}
