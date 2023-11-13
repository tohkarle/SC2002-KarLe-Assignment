package cams.components.input;

import cams.Main;
import cams.interfaces.IntInput;
import cams.utils.Dismiss;

public class GetInt implements IntInput {

    // Input must be an int
    // Input must not be empty

    protected String title;
    protected String input;
    protected int number;

    public GetInt(String title) {
        this.title = title;
        this.input = null;
        this.number = Dismiss.intOption();
    }

    public int getValidInt() {
        while (true) {
            System.out.print(title);
            input = Main.scanner.nextLine();
            if (inputIsEmpty() || inputIsNotInt()) { continue; }
            return number;
        }
    }

    public boolean inputIsNotInt() {
        try { 
            number = Integer.parseInt(input); 
            return false;
        } catch (NumberFormatException e) {
            invalidInput();
            return true;
        }
    }

    public boolean inputIsEmpty() {
        if (!this.input.isEmpty()) { return false; }
        System.out.println("You didn't enter anything. Please try again."); 
        return true;
    }

    public void invalidInput() {
        System.out.println("Invalid input, please try again.");
    }
}
