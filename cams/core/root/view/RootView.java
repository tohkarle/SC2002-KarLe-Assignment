package cams.core.root.view;

import cams.Main;
import cams.core.authentication.view.LogInView;
import cams.core.authentication.view.RegisterView;
import cams.core.root.controller.RootUIController;
import cams.util.UserInput;

public class RootView {

    private int currentUserID;
    private RootUIController rootUIController;

    public RootView() {
        this.currentUserID = -1;
        this.rootUIController = new RootUIController();
    }

    public void show() {
        int option;
        System.out.println("\nWelcome to the Camp Application and Management System");

        // If user is not logged in, let user register or log in
        while (currentUserID == -1) {
            // Let user choose to register or log in
            System.out.println("\nWould you like to register or log in?");
            System.out.println("(1) Register");
            System.out.println("(2) Log In");

            option = UserInput.optionInput();

            switch(option) {
                case 1:
                    RegisterView registerView = new RegisterView(this);
                    registerView.show();
                    break;
                case 2:
                    LogInView logInView = new LogInView(this);
                    logInView.show();
                    break;
            }
        }

        // If user is staff, show staff interface, else if user is student, show student interface
        if (Main.userManager.isStaff(currentUserID)) {
            System.out.println("\nYou are now logged in as Staff " + Main.userManager.getName(this.currentUserID) + "!");

        } else if (Main.userManager.isStudent(currentUserID)) {
            System.out.println("\nYou are now logged in as Student " + Main.userManager.getName(this.currentUserID) + "!");
        }
    }

    public void setCurrentUserID(int currentUserID) {
        this.currentUserID = currentUserID;
    }
}
