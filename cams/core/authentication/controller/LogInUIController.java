package cams.core.authentication.controller;

import cams.Main;

public class LogInUIController {
    
    public void logIn(String userID, String password) {
        if (Main.userManager.isValidUser(userID) != null){

        if (Main.userManager.checkPassword(userID, password)){

            // password accepted
            System.out.println("Credentials accepted!");

            // Main.userManager.logon(userID);
            Main.userManager.save(); // save all users
        }
        else{
            // password wrong, rejected
            System.out.println("Invalid password");
            }
        }
        else{
            System.out.println("UserID not found!");
        }
    }
}
