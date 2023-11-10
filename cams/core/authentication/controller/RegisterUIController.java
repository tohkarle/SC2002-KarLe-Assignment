package cams.core.authentication.controller;

import cams.Main;

public class RegisterUIController {

    public int register(String name, String password, String faculty, boolean isStaff) {
        if (Main.userManager.nameAlreadyExists(name)) {
            System.out.println("Name already used, please enter another name.");
            return -1;
        }
        return Main.userManager.registerUser(name, password, faculty, isStaff);
    }
}
