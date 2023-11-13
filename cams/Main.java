package cams;

import java.util.Scanner;

import cams.managers.AuthManager;
import cams.managers.CampManager;
import cams.managers.EnquiryManager;
import cams.managers.SuggestionManager;
import cams.managers.UserManager;
import cams.views.root.RootView;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static AuthManager authManager = new AuthManager();
    public static CampManager campManager = new CampManager();
    public static EnquiryManager enquiryManager = new EnquiryManager();
    public static SuggestionManager suggestionManager = new SuggestionManager();


    // We can't determine whether it is a student or staff logging in at this point
    public static UserManager userManager;

    public static void main(String args[]) {

        // Fetching all users, camps, enquiries and suggestions from 'database'
        authManager.load();
        campManager.load();
        enquiryManager.load();
        suggestionManager.load();

        // Display root view
        RootView rootView = new RootView();
        rootView.body();

        // Save users, camps, enquiries and suggestions
        authManager.save();
        campManager.save();
        enquiryManager.save();
        suggestionManager.save();

        scanner.close();
    }
}
