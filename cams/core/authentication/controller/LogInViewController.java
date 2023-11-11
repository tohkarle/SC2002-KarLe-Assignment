package cams.core.authentication.controller;

import cams.Main;

public class LogInViewController {
    
    public int logIn(String email, String password) {
        if (!Main.authManager.isValidUser(email)) {
            System.out.println("Email not found!");
            return -1;
        }

        if (Main.authManager.checkPassword(email, password)){
            // password accepted
            return Main.authManager.getUserID(email);

        } else {
            // password wrong, rejected
            System.out.println("Wrong password, please try again.");
            return -1;
        }
    }
}
