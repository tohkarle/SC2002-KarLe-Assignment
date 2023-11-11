package cams.util;

import cams.Main;
import cams.manager.StaffManager;
import cams.manager.StudentManager;
import cams.manager.UserManager;

public class UserManagerFactory {
    public static UserManager createUserManager(int userID) {
        if (Main.authManager.isStudent(userID)) {
            return new StudentManager();
        } else if (Main.authManager.isStaff(userID)) {
            return new StaffManager();
        } else {
            return new UserManager();
        }
    }
}