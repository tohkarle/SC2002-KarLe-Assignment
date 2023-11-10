package cams.core.root.view;

import cams.Main;
import cams.core.authentication.view.LogInUI;
import cams.core.authentication.view.RegisterUI;
import cams.core.root.controller.RootUIController;
import cams.util.UserInput;

public class RootUI {

    private int currentUserID;
    private String currentUserName;
    private RootUIController rootUIController;

    public RootUI() {
        this.currentUserID = -1;
        this.currentUserName = null;
        this.rootUIController = new RootUIController();
    }

    public void showUI() {

        int option;

        System.out.println("\nWelcome to the Camp Application and Management System");

        // If user is not logged in, let user register or log in
        while (currentUserID == -1 && currentUserName == null) {
            // Let user choose to register or log in
            System.out.println("\nWould you like to register or log in?");
            System.out.println("(1) Register");
            System.out.println("(2) Log In");

            option = UserInput.optionInput();

            switch(option) {
                case 1:
                    RegisterUI registerUI = new RegisterUI(this);
                    registerUI.showUI();
                    break;
                case 2:
                    LogInUI logInUI = new LogInUI(this);
                    logInUI.showUI();
                    break;
            }
        }

        // If user is staff, show staff interface, else if user is student, show student interface
        if (Main.userManager.isStaff(currentUserID)) {
            System.out.println("\nYou are now logged in as Staff " + this.currentUserName + "!");

        } else if (Main.userManager.isStudent(currentUserID)) {
            System.out.println("\nYou are now logged in as Student " + this.currentUserName + "!");
        }
    }

    public void setCurrentUserID(int currentUserID) {
        this.currentUserID = currentUserID;
    }

    public void setCurrentUserName(String currentUserName) {
        this.currentUserName = currentUserName;
    }
}
