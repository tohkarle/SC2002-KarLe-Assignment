package cams.components.input;

import cams.Main;
import cams.interfaces.StringInput;
import cams.utils.Dismiss;

public class GetString implements StringInput {

    // Input must not be empty

    private String input;

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

    public boolean inputIsEmpty() {
        if (!this.input.isEmpty()) { return false; }
        System.out.println("You didn't enter anything. Please try again."); 
        return true;
    }

}
