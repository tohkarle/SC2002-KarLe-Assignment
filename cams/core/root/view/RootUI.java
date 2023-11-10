package cams.core.root.view;

import cams.Main;
import cams.core.authentication.view.LogInUI;
import cams.core.root.controller.RootUIController;

public class RootUI {

    private int currentUserID;
    private RootUIController rootUIController;

    public RootUI() {
        this.currentUserID = -1;
        this.rootUIController = new RootUIController();
    }

    public void showUI() {

        int option;

        System.out.println("\nWelcome to the Camp Application and Management System");

        // If user is not logged in, let user register or log in
        while (currentUserID == -1) {
            // Let user choose to register or log in
            System.out.println("\n(1) Register");
            System.out.println("(2) Log In");

            option = Main.scanner.nextInt();

            switch(option) {
                case 1:
                    break;
                case 2:
                    LogInUI logInUI = new LogInUI(this);
                    logInUI.showUI();
                    break;
            }
        }

        // If user is staff, show staff interface, else if user is student, show student interface
        if (Main.userManager.isStaff(currentUserID)) {

        } else if (Main.userManager.isStudent(currentUserID)) {

        }
    }

    public void setCurrentUserID(int currentUserID) {
        this.currentUserID = currentUserID;
    }
}
