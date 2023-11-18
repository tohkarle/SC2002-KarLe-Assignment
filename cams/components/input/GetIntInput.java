package cams.components.input;

import cams.Main;
import cams.interfaces.IntInput;
import cams.utils.Dismiss;

public class GetIntInput implements IntInput {

    // Input must be an int
    // Input must not be empty

    protected String input;
    protected int number;

    public GetIntInput() {
        this.input = null;
        this.number = Dismiss.intOption();
    }

    @Override
    public int getValidInt(String title) {
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
