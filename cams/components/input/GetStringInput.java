package cams.components.input;

import cams.Main;
import cams.interfaces.StringInput;
import cams.utils.Dismiss;


/**
 * Input object to get a valid string input
 */
public class GetStringInput implements StringInput {

    // Input must not be empty

    private String input;

    /**
     * A method to get a valid string input
     * @param title The title of what the input is for
     * @return String
     */
    @Override
    public String getValidString(String title) {
        while (true) {
            System.out.print(title);
            this.input = Main.scanner.nextLine();
            if (this.inputIsEmpty()) { continue; }
            if (this.input.equals(Dismiss.stringOption())) { return this.input; }
            return this.input;
        }
    }


    /**
     * A method to check if the input in this object is empty
     * @return boolean of whether the input is empty
     */
    public boolean inputIsEmpty() {
        if (!this.input.isEmpty()) { return false; }
        System.out.println("You didn't enter anything. Please try again."); 
        return true;
    }

}
