package cams.component;

import cams.Main;

public class SelectionInput {

    public static int intInputFieldWithBack(String title, int min, int max) {
        System.out.print(title);
        int option = Main.scanner.nextInt();

        while (option < min || option > max) {
            if (option == -1) { return -1; }  // go back to previous page when user enters -1

            System.out.println("Invalid input, please try again.");
            System.out.print(title);
            while (!Main.scanner.hasNextInt()) {
                System.out.println("Invalid input, please try again.");
                System.out.print(title);
                Main.scanner.next();  // discard the invalid input
            }
            option = Main.scanner.nextInt();
        }

        Main.scanner.nextLine();
        return option;
    }

    public static int selectionInputFieldWithBack(int min, int max) {
        System.out.println("");
        int option = intInputFieldWithBack("Your selection: ", min, max);
        return option;
    }

    public static int selectionInputFieldWithoutBack(int min, int max) {
        System.out.println("");
        System.out.print("Your selection: ");
        int option = Main.scanner.nextInt();

        while (option < min || option > max) {
            System.out.println("Invalid input, please try again.");
            System.out.print("Your selection: ");
            while (!Main.scanner.hasNextInt()) {
                System.out.println("Invalid input, please try again.");
                System.out.print("Your selection: ");
                Main.scanner.next(); // discard the invalid input
            }
            option = Main.scanner.nextInt();
        }

        Main.scanner.nextLine();
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
