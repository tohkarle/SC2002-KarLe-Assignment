package cams.components.input;

import java.time.LocalDate;

import cams.interfaces.DateInput;
import cams.interfaces.Input;
import cams.interfaces.IntInput;
import cams.interfaces.StringInput;

public class GetInput implements Input {

    private IntInput getInt;
    private StringInput getString;
    private DateInput getDate;
    private IntInput confirm;

    public GetInput() {
        getInt = new GetIntInput();
        getString = new GetStringInput();
        getDate = new GetDateInput();
        confirm = new ConfirmOrDiscard();
    }

    @Override
    public int getValidInt(String title) {
        return getInt.getValidInt(title);
    }

    @Override
    public String getValidString(String title) {
        return getString.getValidString(title);
    }

    @Override
    public LocalDate getValidDate(String title) {
        return getDate.getValidDate(title);
    }

    @Override
    public int confirmOrDiscard(String title) {
        return confirm.getValidInt(title);
    }
}
