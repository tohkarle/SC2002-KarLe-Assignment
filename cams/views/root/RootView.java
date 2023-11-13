package cams.views.root;

import cams.Main;
import cams.components.LoadingIndicator;
import cams.components.option.AuthenticateOptions;
import cams.components.option.Options;
import cams.interfaces.View;
import cams.managers.StaffManager;
import cams.managers.StudentManager;
import cams.managers.UserManager;
import cams.utils.AuthUtil;
import cams.views.authentication.LogInView;
import cams.views.authentication.RegisterView;
import cams.views.user.StaffOptionsView;
import cams.views.user.StudentOptionsView;

public class RootView {

    private UserManager userManager;
    private AuthUtil authenticate;
    private Options authenticateOptions;

    // Views to navigate to
    private View registerView;
    private View logInView;
    private View staffOptionsView;
    private View studentOptionsView;

    public RootView() {
        this.authenticate = new AuthUtil();
        this.authenticateOptions = new AuthenticateOptions();
        this.registerView = new RegisterView(authenticate);
        this.logInView = new LogInView(authenticate);
    }

    public void body() {
        System.out.println("\nWelcome to the Camp Application and Management System");
        while (true) {
             if (authenticate.getUserID() == -1) {
                // Authenticate user
                authenticateOptions.display("Would you like to register or log in?");
                int option = authenticateOptions.selection();

                switch(option) {
                    case 1:
                        registerView.body();
                        break;
                    case 2:
                        logInView.body();
                        break;
                    case 3:
                        LoadingIndicator.terminateAppLoadingIndicator();
                        return;
                }
             } else {
                // Show staff or student options
                userOptions();
             }
        }
    }

    public void userOptions() {
        if (Main.authManager.isStaff(authenticate.getUserID())) {
            userManager = new StaffManager();

            staffOptionsView = new StaffOptionsView(this);
            staffOptionsView.body();

        } else if (Main.authManager.isStudent(authenticate.getUserID())) {
            userManager = new StudentManager();

            studentOptionsView = new StudentOptionsView(this);
            studentOptionsView.body();
        }
    }

    public int getCurrentUserID() {
        return this.authenticate.getUserID();
    }

    public UserManager getManager() {
        return this.userManager;
    }

    public void logUserOut() {
        authenticate.logOut();
    }
}
