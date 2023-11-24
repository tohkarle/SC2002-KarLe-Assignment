package cams.utils;

import java.io.IOException;

import cams.Main;

/**
 * Utility class for printing loading indicators between different views / user actions
 */
public class LoadingIndicator {

    private static final int DELAY_MILLISECONDS = 666;

    /**
     * Prints a custom loading indicator for user's action
     * @param title1 The title of the loading indicator
     * @param title2 The title of the warning indicator
     */
    public static void customLoadingIndicator(String title1, String title2) {
        System.out.println("\n" + title1);
        delay();
        System.out.println(title2 + " (Press Enter to dismiss)");
        Main.scanner.nextLine();
    }

    /**
     * Prints a custom warning indicator
     * @param title The title of the warning indicator
     */
    public static void customWarningIndicator(String title) {
        System.out.println("\n" + title + " (Press Enter to dismiss)");
        Main.scanner.nextLine();
    }

    /**
     * Prints a loading indicator for logging in
     */
    public static void logInLoadingIndicator() {
        System.out.println("\nLogging in...");
        delay();
        System.out.println("Log in successful!");
        delay();
    }

    /**
     * Prints a loading indicator for logging out
     */
    public static void logOutLoadingIndicator() {
        System.out.println("\nLogging out...");
        delay();
        System.out.println("Log out successful!");
        delay();
    }

    /**
     * Prints a loading indicator for user's creation
     */
    public static void createLoadingIndicator(String title) {
        System.out.println("\nCreating " + title + "...");
        delay();
        System.out.println("Create " + title + " successful!");
        delay();
    }

    /**
     * Prints a loading indicator for user's deletion
     */
    public static void deleteLoadingIndicator(String title) {
        System.out.println("\nDeleting " + title + "...");
        delay();
        System.out.println("Delete " + title + " successful!");
        delay();
    }

    /**
     * Prints a loading indicator for user's registration
     */
    public static void registerLoadingIndicator(String title) {
        System.out.println("\nRegistering for " + title + "...");
        delay();
        System.out.println("Register for " + title + " successful!");
        delay();
    }

    /**
     * Prints a loading indicator for user successfully updating
     */
    public static void updateLoadingIndicator(String title) {
        System.out.println("\nUpdating " + title + "...");
        delay();
        System.out.println("Update " + title + " successful!");
        delay();
    }

    /**
     * Prints a loading indicator for terminating the application
     */
    public static void terminateAppLoadingIndicator() {
        System.out.println("\nTerminating app...");
        delay();
        System.out.println("Terminate app successful!");
        delay();
    }

    /**
     * Prints a loading indicator for user withdrawing from camp
     */
    public static void withdrawLoadingIndicator(String title) {
        System.out.println("\nWithdrawing from " + title + "...");
        delay();
        System.out.println("Withdraw from " + title + " successful!");
        delay();
    }

    /**
     * Prints a loading indicator for user editing action
     */
    public static void editLoadingIndicator(String title) {
        System.out.println("\nEditing " + title + "...");
        delay();
        System.out.println("Edit " + title + " successful!");
        delay();
    }

    /**
     * Prints a loading indicator for user's submission
     */
    public static void submitLoadingIndicator(String title) {
        System.out.println("\nSubmitting " + title + "...");
        delay();
        System.out.println("Submit " + title + " successful!");
        delay();
    }

    /**
     * Prints a loading indicator for user's processing
     */
    public static void processLoadingIndicator(String title) {
        System.out.println("\nProcessing " + title + "...");
        delay();
        System.out.println("Process " + title + " successful!");
        delay();
    }

    private static void delay() {
        try {
            while (System.in.available() > 0) {
                System.in.read();
            }
            Thread.sleep(DELAY_MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

