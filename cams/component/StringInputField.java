package cams.component;

import cams.Main;
import cams.util.Dismiss;

public class StringInputField implements StringInput {

    // Input must not be empty

    private String title;
    private String input;

    public StringInputField(String title) {
        this.title = title;
    }

    public String getValidInput() {
        while (true) {
            System.out.print(this.title);
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
