package cams.ui.camp;

import cams.interfaces.Input;
import cams.interfaces.UI;
import cams.model.Camp;
import cams.utils.Dismiss;

public class CampStaffInChargeUI implements UI {

    private Input getInput;
    private Camp camp;
    private String title;

    public CampStaffInChargeUI(Input getInput, Camp camp, String title) {
        this.getInput = getInput;
        this.camp = camp;
        this.title = title;
    }

    @Override
    public void body() {
        String staffName = getInput.getValidString(title);
        if (staffName.equals(Dismiss.stringOption())) { return; }
        camp.setStaffInCharge(staffName);
    }
}
