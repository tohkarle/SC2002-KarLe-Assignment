package cams.utils;

public class Page {
    public static void header(String title) {
        System.out.println("\n" + title);
        System.out.println("(" + Dismiss.intOption() + ") Back");
    }

    public static void clearTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
