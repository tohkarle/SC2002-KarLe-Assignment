package cams.core.authentication.controller;

import cams.Main;

public class RegisterUIController {
    public void register(String name, String password, String faculty) {

        if (Main.userManager.nameAlreadyExists(name)) {
            System.out.println("Name already used, please enter another name.");
            return;
        }

        Main.userManager.registerUser(name, password, faculty);
    }
}
