package cams.core.root.view;

import cams.Main;
import cams.component.LoadingIndicator;
import cams.component.UserInput;
import cams.core.authentication.view.LogInView;
import cams.core.authentication.view.RegisterView;
import cams.core.user.view.StaffActionsView;
import cams.core.user.view.StudentActionsView;
import cams.manager.StaffManager;
import cams.manager.StudentManager;
import cams.manager.UserManager;

public class RootView {

    private int currentUserID;
    private UserManager userManager;

    public RootView() {
        this.currentUserID = -1;
    }

    public void show() {
        int option;

        System.out.println("\nWelcome to the Camp Application and Management System");

        while (true) {
             // If user is not logged in, let user register or log in
             if (currentUserID == -1) {
                // Let user choose to register or log in
                System.out.println("\nWould you like to register or log in?");
                System.out.println("(1) Register");
                System.out.println("(2) Log In");
                System.out.println("(3) Terminate app");

                option = UserInput.selectionInputField(1, 3);

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
                        LoadingIndicator.terminateAppLoadingIndicator();
                        return;
                }
             } else {

                // If user is staff, show staff interface, else if user is student, show student interface
                if (Main.authManager.isStaff(currentUserID)) {
                    userManager = new StaffManager();

                    StaffActionsView staffActionsView = new StaffActionsView(this);
                    staffActionsView.show();

                } else if (Main.authManager.isStudent(currentUserID)) {
                    userManager = new StudentManager();

                    StudentActionsView studentProfileView = new StudentActionsView(this);
                    studentProfileView.show();
                }
             }

        }
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