package cams.util;

import cams.Main;

public class UIComponents {

    public static int intInputField(String title, int min, int max) {
        System.out.print(title);
        int option = Main.scanner.nextInt();
        
        if (option == -1) { return -1; }

        while (option < min || option > max) {
            System.out.println("Invalid input, please try again.");
            System.out.print(title);
            while (!Main.scanner.hasNextInt()) {
                System.out.println("Invalid input, please try again.");
                System.out.print(title);
                Main.scanner.next(); // discard the invalid input
            }
            option = Main.scanner.nextInt();
        }

        Main.scanner.nextLine();
        return option;
    }

    public static int navigationInput(int min, int max) {
        System.out.println("");
        int option = intInputField("Your selection: ", min, max);
        return option;
    }

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

    public static String backOptionString() {
        return "-1";
    }

    public static int backOptionInt() {
        return -1;
    }

    public static void invalidUserInput() {
        System.out.println("Invalid input, please try again\n");
    }

    public static void terminateAppLoadingIndicator() {
        System.out.println("Terminating app...");
        System.out.println("Terminate app successful!\n");
    }

    public static void confirmOrDiscard(String title) {
        System.out.print("Confirm " + title + "? (1) Confirm (2) Discard and go back: ");
    }

    public static void pageHeader(String title) {
        System.out.println("\n" + title);
        System.out.println("(-1) Back");
    }
}
