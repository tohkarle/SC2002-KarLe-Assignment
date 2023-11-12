package cams.component;

import cams.Main;

public class SelectionInput {

    public static int intInputFieldWithBack(String title, int min, int max) {
        int option;

        while (true) {
            System.out.print(title);
            String input = Main.scanner.nextLine();

            if (input.isEmpty()) {
                System.out.println("You didn't enter anything. Please try again.");
                continue;
            }

            try {
                option = Integer.parseInt(input);
                if (option == -1) { return -1; }
                if (option < min || option > max) {
                    System.out.println("Invalid input, please try again.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please try again.");
            }
        }

        return option;
    }

    public static int selectionInputFieldWithBack(int min, int max) {
        System.out.println("");
        int option = intInputFieldWithBack("Your selection: ", min, max);
        return option;
    }

    public static int selectionInputFieldWithoutBack(int min, int max) {
        int option;
        System.out.println("");

        while (true) {
            System.out.print("Your selection: ");
            String input = Main.scanner.nextLine();

            if (input.isEmpty()) {
                System.out.println("You didn't enter anything. Please try again.");
                continue;
            }

            try {
                option = Integer.parseInt(input);
                if (option < min || option > max) {
                    System.out.println("Invalid input, please try again.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please try again.");
            }
        }

        return option;
    }

    public static String backOptionString() {
        return "-1";
    }

    public static int backOptionInt() {
        return -1;
    }

    public static int confirmOrDiscard(String title) {
        return SelectionInput.intInputFieldWithBack("Confirm " + title + "? (1) Confirm (2) Discard and go back: ", 1, 2);
    }

    public static void pageHeader(String title) {
        System.out.println("\n" + title);
        System.out.println("(-1) Back");
    }
}
