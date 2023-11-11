package cams.util;

import cams.Main;

public class UIComponents {
    public static int userInput() {
        int option;
        System.out.print("\nYour selection: ");
        option = Main.scanner.nextInt();
        System.out.print("\n");
        Main.scanner.nextLine();
        return option;
    }

    public static void logInLoadingIndicator() {
        System.out.println("\nLogging in...");
        System.out.println("Log in successful!\n");
    }

    public static void logOutLoadingIndicator() {
        System.out.println("Logging out...");
        System.out.println("Log out successful!\n");
    }

    public static boolean backOption(int option) {
        System.out.println("(" + option+ ")" + " Back");
        if (UIComponents.userInput() == option); { return false; }
    }
}
