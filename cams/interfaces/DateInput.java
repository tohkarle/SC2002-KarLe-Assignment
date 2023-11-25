package cams.interfaces;

import java.time.LocalDate;


/**
 * Interface classes to ensure the input object has method to
 * prompt user for date input
 */
public interface DateInput {


    /**
     * Gets user to input a date, ensuring user inputs valid date
     * @param title The title of what the date is for
     * @return LocalDate object for the date the user has inputed
     */
    public LocalDate getValidDate(String title);
}
