package cams.core.authentication.controller;

import cams.Main;

public class RegisterViewController {

    public int register(String email, String name, String password, String faculty, int option) {

        if (Main.authManager.emailAlreadyExists(email)) {
            System.out.println("Email already used, please enter another email.");
            return -1;
        }

        boolean isStaff = false;
        if (option == 1) { isStaff = true; }

        return Main.authManager.registerUser(email, name, password, faculty, isStaff);
    }
}
