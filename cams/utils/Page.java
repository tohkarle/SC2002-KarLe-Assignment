package cams.utils;

public class Page {
    public static void header(String title) {
        System.out.println("\n" + title);
        System.out.println("(" + Dismiss.intOption() + ") Back");
    }
}
