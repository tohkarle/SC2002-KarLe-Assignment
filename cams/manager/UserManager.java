package cams.manager;

import cams.model.Staff;
import cams.model.User;


/**
 * A high level controller to manage user objects
 * is a singleton object
 */
public class UserManager {

    /**
     * A singleton reference to this object
     */
    private static UserManager instance;

    /**
     * A reference to the current logged in user object
     */
    private User currentUser;


    /**
     * A public static method for other objects to get this object
     * @return UserManager object, this object
     */
    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }


    /**
     * A method to get the current user object
     * @return User object
     */
    public User getCurrentUser() {
        return this.currentUser;
    }


    /**
     * A method to set the current user logged in
     * @param user The user object
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }


    /**
     * A method to check if the current user is a staff
     * @return boolean of whether current user is a staff
     */
    public boolean isStaff() {
        return (this.currentUser instanceof Staff);
    }


    /**
     * A method to update the password for the current user
     * @param newPassword The new password
     */
    public void updatePassword(String newPassword) {
        AuthManager.getInstance().updatePassword(currentUser.getName(), newPassword);
    }


    /**
     * A method to check that there is a user logged on
     * @return boolean of whether a user is currently logged on
     */
    public boolean isAuthenticated() {
        return (this.currentUser != null);
    }
}