package cams.components.input;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import cams.Main;
import cams.interfaces.DateInput;
import cams.utils.Dismiss;

public class GetDate implements DateInput {

    // Input must be in the right format yyyy-MM-dd
    // Input must not be empty

    protected String input;
    protected LocalDate date;

    public GetDate () {
    }

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

    public boolean inputIsEmpty() {
        if (!this.input.isEmpty()) { return false; }
        System.out.println("You didn't enter anything. Please try again."); 
        return true;
    }
}
