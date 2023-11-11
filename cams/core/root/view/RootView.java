package cams.core.root.view;

import cams.Main;
import cams.core.authentication.view.LogInView;
import cams.core.authentication.view.RegisterView;
import cams.core.profile.view.StudentActionsView;
import cams.manager.StaffManager;
import cams.manager.StudentManager;
import cams.manager.UserManager;
import cams.util.UIComponents;

public class RootView {

    private boolean running;
    private int currentUserID;
    private UserManager userManager;

    public RootView() {
        this.running = true;
        this.currentUserID = -1;
    }

    public void show() {
        int option;

        System.out.println("\nWelcome to the Camp Application and Management System\n");

        do {
             // If user is not logged in, let user register or log in
             if (currentUserID == -1) {
                // Let user choose to register or log in
                System.out.println("Would you like to register or log in?");
                System.out.println("(1) Register");
                System.out.println("(2) Log In");
                System.out.println("(3) Terminate app");

                option = UIComponents.userInput();

                switch(option) {
                    case 1:
                        RegisterView registerView = new RegisterView(this);
                        registerView.show();
                        break;
                    case 2:
                        LogInView logInView = new LogInView(this);
                        logInView.show();
                        break;
                    case 3:
                        this.running = false;
                        UIComponents.terminateAppLoadingIndicator();
                        break;
                    default:
                        UIComponents.invalidUserInput();
                        break;
                }
             } else {

                UIComponents.logInLoadingIndicator();

                // If user is staff, show staff interface, else if user is student, show student interface
                if (Main.authManager.isStaff(currentUserID)) {
                    userManager = new StaffManager();

                } else if (Main.authManager.isStudent(currentUserID)) {
                    userManager = new StudentManager();

                    StudentActionsView studentProfileView = new StudentActionsView(this);
                    studentProfileView.show();
                }
             }

        } while (running);
    }

    public UserManager getManager() {
        return this.userManager;
    }

    public int getCurrentUserID() {
        return this.currentUserID;
    }

    public void setCurrentUserID(int currentUserID) {
        this.currentUserID = currentUserID;
    }

    public void logUserOut() {
        this.currentUserID = -1;
    }
}
