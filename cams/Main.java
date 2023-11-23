package cams;

import java.util.Scanner;

import cams.interfaces.ViewHandler;
import cams.manager.NavigationManager;

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
     * The launch point of the CAMS
     */
    public static void main(String args[]) {

        // Initialize NavigationManager - manages navigation flow of views
        ViewHandler navigationManager = new NavigationManager();

        // Initialize root view
        navigationManager.initializeView();

        // Display view according to user selection - displays root view first
        navigationManager.displayView();

        // Close the scanner to release system resources
        scanner.close();
    }
}
