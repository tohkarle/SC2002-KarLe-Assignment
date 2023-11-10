package cams.util;

import cams.Main;

public class UserInput {
    public static int optionInput() {
        int option;
        System.out.print("\nYour selection: ");
        option = Main.scanner.nextInt();
        System.out.print("\n");
        Main.scanner.nextLine();
        return option;
    }
}
