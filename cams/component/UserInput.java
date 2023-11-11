package cams.component;

import cams.Main;

public class UserInput {

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

    public static int selectionInputField(int min, int max) {
        System.out.println("");
        int option = intInputField("Your selection: ", min, max);
        return option;
    }

    public static String backOptionString() {
        return "-1";
    }

    public static int backOptionInt() {
        return -1;
    }

    public static void confirmOrDiscard(String title) {
        System.out.print("Confirm " + title + "? (1) Confirm (2) Discard and go back: ");
    }

    public static void pageHeader(String title) {
        System.out.println("\n" + title);
        System.out.println("(-1) Back");
    }
}
