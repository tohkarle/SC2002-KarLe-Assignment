package cams.ui.camp;

import java.time.LocalDate;

import cams.Main;
import cams.components.input.GetDateInput;
import cams.interfaces.InputField;
import cams.utils.CampUtil;

public class CampRegiatrationClosingDateUI extends GetDateInput implements InputField {

    private CampUtil campUtil;
    private LocalDate registrationClosingDate;

    public CampRegiatrationClosingDateUI(CampUtil campUtil, String title) {
        super(title);
        this.campUtil = campUtil;
    }

    @Override
    public boolean focused() {
        registrationClosingDate = super.getValidDate();
        if (registrationClosingDate == null) { return false; }

        // error, registration close date after camp start
        if (!registrationClosingDate.isBefore(Main.campManager.getStartDate(campUtil.getId()))){
            System.out.println("Error, date is after camp start!");
            System.out.println("Registration closing date not changed!");
        }
        
        campUtil.setRegistrationClosingDate(registrationClosingDate);
        return true;
    }
}
