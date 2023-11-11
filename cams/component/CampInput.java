package cams.component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import cams.Main;

public class CampInput {
    public static String stringField(String title) {
        System.out.print(title);
        return Main.scanner.nextLine();
    }

    public static int intField(String title) {
        return UserInput.intInputField(title, 1, 2);
    }

    public static LocalDate dateField(String title) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            System.out.print(title);
            String dateString = Main.scanner.nextLine();
            if (dateString.equals(UserInput.backOptionString())) { return null; }

            try {
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in the format 'yyyy-MM-dd'.");
            }
        }
    }

    public static LocalDate endDateField(LocalDate startDate, String title) {
        LocalDate endDate;
        while (true) {
            endDate = CampInput.dateField(title);
            if (endDate == null) { return null; }
            if (endDate.isEqual(startDate) || endDate.isAfter(startDate)) { break; }
            System.out.println("End date cannot be before start date.");
        }
        return endDate;
    }
}
