package cams.core.authentication.controller;

import cams.Main;

public class RegisterUIController {

    public int register(String email, String name, String password, String faculty, boolean isStaff) {
        if (Main.userManager.emailAlreadyExists(email)) {
            System.out.println("Email already used, please enter another email.");
            return -1;
        }
        return Main.userManager.registerUser(email, name, password, faculty, isStaff);
    }
}
