package cams.utils;

/**
 * Utility class for printing ASCII art
 */
public class AsciiArt {
    
    public static void printCams() {
        int lengthOfLongestLine = " ██████╗ █████╗ ███╗   ███╗███████╗".length();
        String topBorder = "╔" + "═".repeat(lengthOfLongestLine) + "╗";
        String bottomBorder = "╚" + "═".repeat(lengthOfLongestLine) + "╝";

        System.out.println(topBorder);
        System.out.println("  ██████╗ █████╗ ███╗   ███╗███████╗ ");
        System.out.println(" ██╔════╝██╔══██╗████╗ ████║██╔════╝ ");
        System.out.println(" ██║     ███████║██╔████╔██║███████╗ ");
        System.out.println(" ██║     ██╔══██║██║╚██╔╝██║╚════██║ ");
        System.out.println(" ╚██████╗██║  ██║██║ ╚═╝ ██║███████║ ");
        System.out.println("  ╚═════╝╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝ ");
        System.out.println(bottomBorder);
    }
}
