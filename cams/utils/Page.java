package cams.utils;

/**
 * Utility class that prints the header of a page / menu
 */
public class Page {
    /**
     * Prints the header of a page
     * @param title The title of the page
     */
    public static void header(String title) {
        System.out.println("\n" + title);
        System.out.println("(" + Dismiss.intOption() + ") Back");
    }
}
