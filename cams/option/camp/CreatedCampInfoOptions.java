package cams.option.camp;

import java.util.Arrays;

import cams.interfaces.IntInput;
import cams.manager.CampManager;
import cams.ui.GetSelectionUI;

public class CreatedCampInfoOptions extends CampInfoOptions {

    public CreatedCampInfoOptions(CampManager campManager) {
        super(campManager);
    }

    @Override
    public void updateCampInfo() {
        super.getCampManager().createTempCamp();
        super.updateCampInfo();
        super.getOptions().remove(String.format("Staff-in-charge: %s", super.getCampManager().getTempCamp().getStaffInCharge()));
        super.getOptions().addAll(Arrays.asList(
            "(1) Edit camp details",
            "(2) Manage enquiries",
            "(3) Manage suggestions",
            "(4) Create report",
            "(5) Delete Camp"
        ));
    }

    @Override
    public int selection() {
        IntInput selection = new GetSelectionUI(-1, 5);
        return selection.getValidInt("Your selection: ");
    }
}
