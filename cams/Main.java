package cams;

import java.util.Scanner;

import cams.manager.NavigationManager;
import cams.service.AuthService;
import cams.service.CampService;
import cams.service.DependencyService;
import cams.service.EnquiryManager;
import cams.service.SuggestionManager;

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
    public static EnquiryManager enquiryManager;

    /**
     * A reference to the SuggestionManager that is used to load from and save to suggestionMan.sav and SuggestionManagerKey.sav.
     * Acts like an API that we can request required suggestion information from.
     */
    public static SuggestionManager suggestionManager;

    /**
     * The launch point of the CAMS
     */
    public static void main(String args[]) {

        // Initialize DependencyManager - manages dependencies and provides instances of classes
        DependencyService dependencyService = new DependencyService();

        AuthService authService = (AuthService) dependencyService.getInstance("AuthService");
        CampService campService = (CampService) dependencyService.getInstance("CampService");

        // Initialize ViewController - manages navigation flow of views
        NavigationManager navigationManager = (NavigationManager) dependencyService.getInstance("NavigationManager");

        // Initialize root view
        navigationManager.initializeRootView();

        // Display view according to user selection - displays root view first
        navigationManager.displayView();

        // Save user data, camp data, enquiry data, and suggestion data
        authService.save();
        campService.save();
        // enquiryManager.save();
        // suggestionManager.save();

        // Close the scanner to release system resources
        scanner.close();
    }
}
