package cams.option.suggestion;

import java.util.Arrays;

import cams.manager.CampManager;
import cams.manager.SuggestionManager;
import cams.option.camp.CampInfoOptions;

public class EditSuggestionOptions extends CampInfoOptions {

    private SuggestionManager suggestionManager;
    
    public EditSuggestionOptions(CampManager campManager, SuggestionManager suggestionManager) {
        super(campManager);
        this.suggestionManager = suggestionManager;
    }

    @Override
    public void updateCampInfo() {
        super.setCamp(suggestionManager.getTempSuggestion().getCamp());
        super.setCampInfo();
        this.setCampInfo();
    }

    @Override
    public void setCampInfo() {
        super.getOptions().add(0, String.format("Title: %s", suggestionManager.getTempSuggestion().getTitle()));
        super.getOptions().remove(String.format("Staff-in-charge: %s", super.getCamp().getStaffInCharge()));
        super.getOptions().addAll(Arrays.asList(
            "Update changes"
        ));
    }
}
