package cams.option.camp;

import java.util.Arrays;

import cams.manager.CampManager;

public class EditCampInfoOptions extends CampInfoOptions {
    
    public EditCampInfoOptions(CampManager campManager) {
        super(campManager);
    }

    @Override
    public void updateCampInfo() {
        super.setCamp(super.getCampManager().getTempCamp());
        super.setCampInfo();
        this.setCampInfo();
    }

    @Override
    public void setCampInfo() {
        super.getOptions().remove(String.format("Staff-in-charge: %s", super.getCamp().getStaffInCharge()));
        super.getOptions().addAll(Arrays.asList(
            "Update changes"
        ));
    }
}