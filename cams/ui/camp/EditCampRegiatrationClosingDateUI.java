package cams.ui.camp;

import java.time.LocalDate;

import cams.components.input.GetDateInput;
import cams.interfaces.UI;
import cams.model.Camp;

public class EditCampRegiatrationClosingDateUI extends GetDateInput implements UI {

    private Camp camp;
    private String title;

    public EditCampRegiatrationClosingDateUI(Camp camp, String title) {
        this.camp = camp;
        this.title = title;
    }

    @Override
    public void body() {
        LocalDate registrationClosingDate = super.getValidDate(title);
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
