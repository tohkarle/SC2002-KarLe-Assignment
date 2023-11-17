package cams.manager;

import cams.components.LoadingIndicator;
import cams.service.AuthService;

public class AuthManager {

    private AuthService authService;
    private UserManager userManager;

    public AuthManager(AuthService authService, UserManager userManager) {
        this.authService = authService;
        this.userManager = userManager;
    }

    public boolean registerSuccessful(String email, String name, String password, String faculty, boolean isStaff) {
        if (authService.emailAlreadyExists(email)) {
            System.out.println("Email already used, please enter another email.");
            return false;
        }

        int userID = authService.registerUser(email, name, password, faculty, isStaff);
        this.userManager.setCurrentUser(authService.getUser(userID));
        authService.save();
        LoadingIndicator.registerLoadingIndicator("user");
        return true;
    }

    public boolean logInSuccessful(String email, String password) {
        if (!authService.isValidUser(email)) {
            System.out.println("Email not found!");
            return false;
        }

        if (authService.checkPassword(email, password)){
            // password accepted
            LoadingIndicator.logInLoadingIndicator();
            int userID = authService.logIn(email);
            this.userManager.setCurrentUser(authService.getUser(userID));
            return true;
        } else {
            // password wrong, rejected
            System.out.println("Wrong password, please try again.");
            return false;
        }
    }
}
