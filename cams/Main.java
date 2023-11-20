package cams;

import java.util.Scanner;

import cams.manager.AuthManager;
import cams.manager.CampManager;
import cams.manager.EnquiryManager;
import cams.manager.NavigationManager;
import cams.manager.SuggestionManager;
import cams.manager.UserManager;
import cams.service.AuthService;
import cams.service.CampService;
import cams.service.EnquiryService;
import cams.service.SuggestionService;

/**
 * The Launcher for the Camp Application and Management System
 * @author Chen Wei
 */
public class Main {

    /**
     * A scanner that is shared across all classes
     * by calling Main.scanner such that a new Scanner
     * does not have to be called for each class neededing
     * to take input
     */
    public static Scanner scanner = new Scanner(System.in);

    /** 
     * A reference to the AuthManager that is used to load from and save to userMan.sav and UserManagerKey.sav.
     * Acts like an API that we can request required informatio from to perform authentication of user.
     */
    public static AuthService authService;

    /**
     * A reference to the CampManager that is used to load from and save to campMan.sav and CampManagerKey.sav.
     * Acts like an API that we can request required camp information from.
     */
    public static CampService campService;

    /**
     * A reference to the EnquiryManager that is used to load from and save to enquiryMan.sav and EnquiryManagerKey.sav.
     * Acts like an API that we can request required enquiry information from.
     */
    public static EnquiryService enquiryService;

    /**
     * A reference to the SuggestionManager that is used to load from and save to suggestionMan.sav and SuggestionManagerKey.sav.
     * Acts like an API that we can request required suggestion information from.
     */
    public static SuggestionService suggestionService;

    /**
     * The launch point of the CAMS
     */
    public static void main(String args[]) {

        UserManager.getInstance();
        AuthManager.getInstance();
        CampManager.getInstance();
        EnquiryManager.getInstance();
        SuggestionManager.getInstance();

        // Initialize NavigationManager - manages navigation flow of views
        NavigationManager navigationManager = new NavigationManager();

        // Initialize root view
        navigationManager.initializeRootView();

        // Display view according to user selection - displays root view first
        navigationManager.displayView();

        // Save user data, camp data, enquiry data, and suggestion data
        authService.save();
        campService.save();
        enquiryService.save();
        suggestionService.save();

        // Close the scanner to release system resources
        scanner.close();
    }
}
