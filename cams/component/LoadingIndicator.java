package cams.component;

public class LoadingIndicator {
    public static void logInLoadingIndicator() {
        System.out.println("\nLogging in...");
        System.out.println("Log in successful!");
    }

    public static void logOutLoadingIndicator() {
        System.out.println("Logging out...");
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

    public static void updateLoadingIndicator(String title) {
        System.out.println("\nUpdating " + title + "...");
        System.out.println("Update " + title + " successful!");
    }

    public static void terminateAppLoadingIndicator() {
        System.out.println("\nTerminating app...");
        System.out.println("Terminate app successful!\n");
    }
}
