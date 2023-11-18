package cams.option.suggestion;

import java.util.Arrays;

import cams.manager.CampManager;
import cams.option.camp.CampInfoOptions;

public class CreateSuggestionOptions extends CampInfoOptions {

    public CreateSuggestionOptions(CampManager campManager) {
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
            "Done"
        ));
    }
}
