package cams.manager;

import cams.components.LoadingIndicator;
import cams.model.User;
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

        if (authService.nameAlreadyExists(name)) {
            System.out.println("Name already used, please enter another name.");
            return false;
        }

        User newUser = authService.registerUser(email, name, password, faculty, isStaff);
        if (newUser == null) {
            System.out.println("Registration unsuccessful, please try again.");
            return false;
        }
        this.userManager.setCurrentUser(newUser);
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
            User user = authService.logIn(email);
            if (user == null) {
                System.out.println("Log in unsuccessful, please try again.");
                return false;
            }
            this.userManager.setCurrentUser(user);
            return true;
        } else {
            // password wrong, rejected
            System.out.println("Wrong password, please try again.");
            return false;
        }
    }
}
