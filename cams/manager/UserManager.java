package cams.manager;

import cams.model.Staff;
import cams.model.User;

public class UserManager {

    private static UserManager instance;
    private User currentUser;

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public boolean isStaff() {
        return (this.currentUser instanceof Staff);
    }

    public void updatePassword(String newPassword) {
        AuthManager.getInstance().updatePassword(currentUser.getName(), newPassword);
    }

    public boolean isAuthenticated() {
        return (this.currentUser != null);
    }
}