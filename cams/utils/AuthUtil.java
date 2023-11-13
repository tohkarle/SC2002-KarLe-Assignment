package cams.utils;

import java.util.ArrayList;

import cams.Main;
import cams.components.LoadingIndicator;

public class AuthUtil {

    private int userID;
    private ArrayList<Integer> campIDs;
    private ArrayList<Integer> suggestionIDs;
    private ArrayList<Integer> enquiryIDs;

    public AuthUtil() {
        this.userID = -1;
    }

    public boolean registerSuccessful(String email, String name, String password, String faculty, boolean isStaff) {
        if (Main.authManager.emailAlreadyExists(email)) {
            System.out.println("Email already used, please enter another email.");
            return false;
        }

        // Set the currentUserID in rootUI after registering user
        this.userID = Main.authManager.registerUser(email, name, password, faculty, isStaff);
        Main.authManager.save();
        LoadingIndicator.registerLoadingIndicator("user");
        return true;
    }

    public boolean logInSuccessful(String email, String password) {
        if (!Main.authManager.isValidUser(email)) {
            System.out.println("Email not found!");
            return false;
        }

        if (Main.authManager.checkPassword(email, password)){
            // password accepted
            LoadingIndicator.logInLoadingIndicator();
            this.userID = Main.authManager.getUserID(email);
            return true;
        } else {
            // password wrong, rejected
            System.out.println("Wrong password, please try again.");
            return false;
        }
    }

    public void logOut() {
        this.userID = -1;
    }

    public int getUserID() {
        return this.userID;
    }

    public ArrayList<Integer> getCampIDs() {
        return this.campIDs;
    }

    public ArrayList<Integer> getSuggestionIDs() {
        return this.suggestionIDs;
    }

    public ArrayList<Integer> getEnquiryIDs() {
        return this.enquiryIDs;
    }
}
