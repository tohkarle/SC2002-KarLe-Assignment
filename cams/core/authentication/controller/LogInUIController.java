package cams.core.authentication.controller;

import cams.Main;

public class LogInUIController {
    
    public int logIn(String name, String password) {
        if (!Main.userManager.isValidUser(name)) {
            System.out.println("Name not found!");
            return -1;
        }

        if (Main.userManager.checkPassword(name, password)){
            // password accepted
            System.out.println("Credentials accepted!");
            return Main.userManager.getUserID(name);

        } else {
            // password wrong, rejected
            System.out.println("Invalid password");
            return -1;
        }
    }
}
