package cams.manager;

import cams.model.User;
import cams.service.AuthService;
import cams.utils.LoadingIndicator;


/**
 * A high level controller object to manage authentication logic,
 * is a singleton object
 */
public class AuthManager {

    /**
     * A singleton object to this object for other objects to reference
     */
    private static AuthManager instance;

    /**
     * A singleton object to a authentication service object
     */
    private AuthService authService;


    /**
     * Initializes the object
     */
    public AuthManager() {
        this.authService = new AuthService();
    }


    /**
     * A public static method for other objects to be able to reference this object
     * @return AuthManager, this singleton object
     */
    public static AuthManager getInstance() {
        if (instance == null) {
            instance = new AuthManager();
        }
        return instance;
    }


    /**
     * A method to register a new user
     * @param email The email/userID of the new user
     * @param name The name of the new user
     * @param password The password of the new user
     * @param faculty The user group / faculty of the new user
     * @param isStaff boolean of whether this user is a staff
     * @return boolean of whether the registration is successful
     */
    public boolean registerSuccessful(String email, String name, String password, String faculty, boolean isStaff) {
        if (authService.emailAlreadyExists(email)) {
            LoadingIndicator.customLoadingIndicator("Registering...", "Email already used, please enter another email.");
            return false;
        }

        if (authService.nameAlreadyExists(name)) {
            LoadingIndicator.customLoadingIndicator("Registering...", "Name already used, please enter another name.");
            return false;
        }

        User newUser = authService.registerUser(email, name, password, faculty, isStaff);
        if (newUser == null) {
            LoadingIndicator.customLoadingIndicator("Registering...", "Registration unsuccessful, please try again.");
            return false;
        }
        UserManager.getInstance().setCurrentUser(newUser);
        authService.save();
        LoadingIndicator.registerLoadingIndicator("user");
        return true;
    }


    /**
     * A method to check if credentials allow login
     * @param email The email/userID of the user
     * @param password The password of the user
     * @return boolean of whether login was successful
     */
    public boolean logInSuccessful(String email, String password) {
        if (!authService.isValidUser(email)) {
            LoadingIndicator.customLoadingIndicator("Logging in...", "Email not found!");
            return false;
        }

        if (authService.checkPassword(email, password)){
            // password accepted
            LoadingIndicator.logInLoadingIndicator();
            User user = authService.logIn(email);
            if (user == null) {
                LoadingIndicator.customLoadingIndicator("Logging in...", "Log in unsuccessful, please try again.");
                return false;
            }
            UserManager.getInstance().setCurrentUser(user);
            return true;
        } else {
            // password wrong, rejected
            LoadingIndicator.customLoadingIndicator("Logging in...", "Wrong password, please try again.");
            return false;
        }
    }


    /**
     * A method to update the password for a user
     * @param userName The email/userID of the user
     * @param newPassword The new password
     */
    public void updatePassword(String userName, String newPassword) {
        authService.updatePassword(userName, newPassword);
    }
}
