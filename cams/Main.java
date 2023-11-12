package cams;

import java.util.Scanner;

import cams.manager.AuthManager;
import cams.manager.CampManager;
import cams.manager.EnquiryManager;
import cams.manager.SuggestionManager;
import cams.manager.UserManager;
import cams.view.root.RootView;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static AuthManager authManager = new AuthManager();
    public static CampManager campManager = new CampManager();
    public static EnquiryManager enquiryManager = new EnquiryManager();
    public static SuggestionManager suggestionManager = new SuggestionManager();

    // We can't determine whether it is a student or staff logging in at this point
    public static UserManager userManager;

    public static void main(String args[]) {

        // Fetching users, camps, enquiries and suggestions
        authManager.load();
        campManager.load();
        enquiryManager.load();
        suggestionManager.load();

        // Display root view
        RootView rootView = new RootView();
        rootView.show();

        // Save users, camps, enquiries and suggestions
        authManager.save();
        campManager.save();
        enquiryManager.save();
        suggestionManager.save();

        scanner.close();
    }
}
