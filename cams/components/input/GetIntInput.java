package cams.components.input;

import cams.Main;
import cams.interfaces.IntInput;
import cams.utils.Dismiss;


/**
 * Input object to get a valid int input
 */
public class GetIntInput implements IntInput {

    // Input must be an int
    // Input must not be empty

    protected String input;
    protected int number;


    /**
     * Initialize the input object
     */
    public GetIntInput() {
        this.input = null;
        this.number = Dismiss.intOption();
    }

    
    /**
     * A method to get a valid int input
     * @param title The title of what the input is for
     * @return int
     */
    @Override
    public int getValidInt(String title) {
        while (true) {
            System.out.print(title);
            input = Main.scanner.nextLine();
            if (inputIsEmpty() || inputIsNotInt()) { continue; }
            return number;
        }
    }


    /**
     * A method to check if the input in the object is not of type int
     * @return boolean of whether the input is a valid int
     */
    public boolean inputIsNotInt() {
        try { 
            number = Integer.parseInt(input); 
            return false;
        } catch (NumberFormatException e) {
            invalidInput();
            return true;
        }
    }


    /**
     * A method to check if the input in the object is empty
     * @return boolean of whether the input is empty
     */
    public boolean inputIsEmpty() {
        if (!this.input.isEmpty()) { return false; }
        System.out.println("You didn't enter anything. Please try again."); 
        return true;
    }


    /**
     * A method to print when the input is invalid
     */
    public void invalidInput() {
        System.out.println("Invalid input, please try again.");
    }
}
