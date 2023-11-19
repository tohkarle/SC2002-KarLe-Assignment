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
        super.updateCampInfo();
        super.getOptions().remove(String.format("Staff-in-charge: %s", super.getCampManager().getTempCamp().getStaffInCharge()));
        super.getOptions().addAll(Arrays.asList(
            "Submit suggestion"
        ));
    }
}
