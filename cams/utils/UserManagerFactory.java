package cams.utils;

import cams.Main;
import cams.manager.UserManager;
import cams.service.StaffManager;
import cams.service.StudentManager;

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