package cams.component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import cams.Main;

public abstract class DateInput implements IntInput {

    public abstract int getValidInput();

    public static LocalDate dateField(String title) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            System.out.print(title);
            String dateString = Main.scanner.nextLine();
            if (dateString.equals(SelectionInput.backOptionString())) { return null; }

            try {
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in the format 'yyyy-MM-dd'.");
            }
        }
    }
}
