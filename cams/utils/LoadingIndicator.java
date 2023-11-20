package cams.utils;

public class LoadingIndicator {

    public static void logInLoadingIndicator() {
        System.out.println("\nLogging in...");
        System.out.println("Log in successful!");
    }

    public static void logOutLoadingIndicator() {
        System.out.println("\nLogging out...");
        System.out.println("Log out successful!");
    }

    public static void createLoadingIndicator(String title) {
        System.out.println("\nCreating " + title + "...");
        System.out.println("Create " + title + " successful!");
    }

    public static void deleteLoadingIndicator(String title) {
        System.out.println("\nDeleting " + title + "...");
        System.out.println("Delete " + title + " successful!");
    }

    public static void registerLoadingIndicator(String title) {
        System.out.println("\nRegistering for " + title + "...");
        System.out.println("Register for " + title + " successful!");
    }

    public static void updateLoadingIndicator(String title) {
        System.out.println("\nUpdating " + title + "...");
        System.out.println("Update " + title + " successful!");
    }

    public static void terminateAppLoadingIndicator() {
        System.out.println("\nTerminating app...");
        System.out.println("Terminate app successful!\n");
    }

    public static void withdrawLoadingIndicator(String title) {
        System.out.println("\nWithdrawing from " + title + "...");
        System.out.println("Withdraw from " + title + " successful!");
    }

    public static void editingLoadingIndicator(String title) {
        System.out.println("\nEditing " + title + "...");
        System.out.println("Edit " + title + " successful!");
    }

    public static void submitLoadingIndicator(String title) {
        System.out.println("\nSubmitting " + title + "...");
        System.out.println("Submit " + title + " successful!");
    }

    public static void processLoadingIndicator(String title) {
        System.out.println("\nProcessing " + title + "...");
        System.out.println("Process " + title + " successful!");
    }
}
