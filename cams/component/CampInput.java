package cams.component;

import cams.Main;

public class CampInput {
    public static String stringField(String title) {
        System.out.print(title);
        return Main.scanner.nextLine();
    }

    public static int intField(String title) {
        return UserInput.intInputField(title, 1, 2);
    }
}
