package cams.option.suggestion;

import java.util.Arrays;

import cams.manager.CampManager;
import cams.manager.SuggestionManager;
import cams.model.SuggestionStatus;
import cams.option.camp.CampInfoOptions;

public class ProcessSuggestionOptions extends CampInfoOptions {

    private SuggestionManager suggestionManager;
    
    public ProcessSuggestionOptions(CampManager campManager, SuggestionManager suggestionManager) {
        super(campManager);
        this.suggestionManager = suggestionManager;
    }

    @Override
    public void updateCampInfo() {
        suggestionManager.createTempSuggestion();
        super.setCamp(suggestionManager.getTempSuggestion().getCamp());
        super.setCampInfo();
        this.setCampInfo();
    }

    @Override
    public void setCampInfo() {
        super.getOptions().add(0, String.format("Title: %s", suggestionManager.getTempSuggestion().getTitle()));
        super.getOptions().remove(String.format("Staff-in-charge: %s", super.getCamp().getStaffInCharge()));
        if (suggestionManager.getSelectedSuggestionStatus() == SuggestionStatus.PENDING) {
            super.getOptions().addAll(Arrays.asList(
                "(1) Approve suggestion",
                "(2) Reject suggestion"
            ));
        }
    }
}
