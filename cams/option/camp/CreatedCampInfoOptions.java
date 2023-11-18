package cams.option.camp;

import java.util.Arrays;

import cams.manager.CampManager;

public class CreatedCampInfoOptions extends CampInfoOptions {

    public CreatedCampInfoOptions(CampManager campManager) {
        super(campManager);
    }

    @Override
    public void updateCampInfo() {
        super.getCampManager().createTempCamp();
        super.setCamp(super.getCampManager().getSelectedCamp());
        super.setCampInfo();
        this.setCampInfo();
    }

    @Override
    public void setCampInfo() {
        super.getOptions().remove(String.format("Staff-in-charge: %s", super.getCamp().getStaffInCharge()));
        super.getOptions().addAll(Arrays.asList(
            "(1) Edit camp details",
            "(2) Manage enquiries",
            "(3) Manage suggestions",
            "(4) Create report",
            "(5) Delete Camp"
        ));
    }
}
