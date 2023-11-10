package cams;

import java.util.Scanner;

import cams.core.authentication.view.LogInUI;
import cams.manager.CampManager;
import cams.manager.EnquiryManager;
import cams.manager.SuggestionManager;
import cams.manager.UserManager;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static UserManager userManager = new UserManager();
    public static CampManager campManager = new CampManager();
    public static EnquiryManager enquiryManager = new EnquiryManager();
    public static SuggestionManager suggestionManager = new SuggestionManager();

    public static void main(String args[]){

        // Launch the login
        LogInUI logInUI = new LogInUI();
        logInUI.showUI();

        userManager.save();
        campManager.save();
        enquiryManager.save();
        suggestionManager.save();

        scanner.close();
    }
}
