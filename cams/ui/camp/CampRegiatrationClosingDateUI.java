package cams.ui.camp;

import java.time.LocalDate;

import cams.interfaces.Input;
import cams.interfaces.UI;
import cams.model.Camp;
import cams.utils.LoadingIndicator;

public class CampRegiatrationClosingDateUI implements UI {

    private Input getInput;
    private Camp camp;
    private String title;

    public CampRegiatrationClosingDateUI(Input getInput, Camp camp, String title) {
        this.getInput = getInput;
        this.camp = camp;
        this.title = title;
    }

    @Override
    public void body() {
        LocalDate registrationClosingDate = getInput.getValidDate(title);
        if (registrationClosingDate == null) { return; }

        // check if the camp passed in is actually for editing
        // error, registration close date after camp start
        if (camp.getId() != -1 && registrationClosingDate.isAfter(camp.getStartDate())){
            LoadingIndicator.customWarningIndicator("Registration should close before camp starts.");
            return;
        }
        
        camp.setRegistrationClosingDate(registrationClosingDate);
        return;
    }
}
