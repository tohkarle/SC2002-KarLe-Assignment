package cams.core.authentication.controller;

import cams.Main;

public class LogInUIController {
    
    public int logIn(String email, String password) {
        if (!Main.userManager.isValidUser(email)) {
            System.out.println("Email not found!");
            return -1;
        }

        if (Main.userManager.checkPassword(email, password)){
            // password accepted
            System.out.println("Credentials accepted!");
            return Main.userManager.getUserID(email);

        } else {
            // password wrong, rejected
            System.out.println("Invalid password");
            return -1;
        }
    }
}
