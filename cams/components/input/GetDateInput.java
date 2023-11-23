package cams.components.input;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import cams.Main;
import cams.interfaces.DateInput;
import cams.utils.Dismiss;


/**
 * Input object to get input for date
 */
public class GetDateInput implements DateInput {

    // Input must be in the right format yyyy-MM-dd
    // Input must not be empty

    protected String input;
    protected LocalDate date;

    /**
     * Initialize the input object
     */
    public GetDateInput () {
    }


    /**
     * Gets user to input a date, ensuring user inputs valid date
     * @param title The title of what the date is for
     * @return LocalDate object for the date the user has inputed
     */
    @Override
    public LocalDate getValidDate(String title) {
        while (true) {
            System.out.print(title);
            this.input = Main.scanner.nextLine();
            if (this.inputIsEmpty()) { continue; }
            if (this.input.equals(Dismiss.stringOption())) { return null; }
            if (this.inputIsInWrongFormat()) { continue; }
            return this.date;
        }
    }


    /**
     * A method to check whether the date inside this input object is not the correct format
     * @return boolean of whether the input is a valid date format
     */
    public boolean inputIsInWrongFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            this.date = LocalDate.parse(this.input, formatter);
            return false;
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter the date in the format 'yyyy-MM-dd'.");
            return true;
        }
    }

    
    /**
     * A method to check if the current input in this input object is empty
     * @return boolean of whether the input is empty
     */
    public boolean inputIsEmpty() {
        if (!this.input.isEmpty()) { return false; }
        System.out.println("You didn't enter anything. Please try again."); 
        return true;
    }
}
