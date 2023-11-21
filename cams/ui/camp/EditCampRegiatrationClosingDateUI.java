package cams.ui.camp;

import java.time.LocalDate;

import cams.interfaces.Input;
import cams.interfaces.UI;
import cams.model.Camp;

public class EditCampRegiatrationClosingDateUI implements UI {

    private Input getInput;
    private Camp camp;
    private String title;

    public EditCampRegiatrationClosingDateUI(Input getInput, Camp camp, String title) {
        this.getInput = getInput;
        this.camp = camp;
        this.title = title;
    }

    @Override
    public void body() {
        LocalDate registrationClosingDate = getInput.getValidDate(title);
        if (registrationClosingDate == null) { return; }

        // error, registration close date after camp start
        if (registrationClosingDate.isAfter(camp.getStartDate())){
            System.out.println("Error, registration should close before camp starts!");
            System.out.println("Registration closing date not changed!");
        }
        
        camp.setRegistrationClosingDate(registrationClosingDate);
        return;
    }
}
