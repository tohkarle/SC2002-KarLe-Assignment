package cams.components.input;

import java.time.LocalDate;

import cams.interfaces.DateInput;
import cams.interfaces.Input;
import cams.interfaces.IntInput;
import cams.interfaces.StringInput;


/**
 * Input object that holds other input objects
 */
public class GetInput implements Input {
    
    private IntInput getInt;
    private StringInput getString;
    private DateInput getDate;
    private IntInput confirm;

    /**
     * Initialize the input object
     */
    public GetInput() {
        getInt = new GetIntInput();
        getString = new GetStringInput();
        getDate = new GetDateInput();
        confirm = new ConfirmOrDiscard();
    }


    /**
     * A method to get a valid int input
     * @param title The title of what the input is for
     * @return int
     */
    @Override
    public int getValidInt(String title) {
        return getInt.getValidInt(title);
    }


    /**
     * A method to get a valid string input
     * @param title The title of what the input is for
     * @return String
     */
    @Override
    public String getValidString(String title) {
        return getString.getValidString(title);
    }


    /**
     * A method to get a valid date input
     * @param title The title of what the input is for
     * @return LocalDate object
     */
    @Override
    public LocalDate getValidDate(String title) {
        return getDate.getValidDate(title);
    }


    /**
     * A method to get confirmation or discard
     * @param title The title of what the input is for
     * @return int, 1 for confirm, 2 for back
     */
    @Override
    public int confirmOrDiscard(String title) {
        return confirm.getValidInt(title);
    }
}
