package cams.option.suggestion;

import java.util.Arrays;

import cams.interfaces.IntInput;
import cams.manager.CampManager;
import cams.manager.SuggestionManager;
import cams.model.SuggestionStatus;
import cams.option.camp.CampInfoOptions;
import cams.ui.GetSelectionUI;

public class CreatedSuggestionOptions extends CampInfoOptions {

    private SuggestionManager suggestionManager;
    
    public CreatedSuggestionOptions(CampManager campManager, SuggestionManager suggestionManager) {
        super(campManager);
        this.suggestionManager = suggestionManager;
    }

    @Override
    public void updateCampInfo() {
        suggestionManager.createTempSuggestion();
        super.updateCampInfo();
        super.getOptions().add(0, String.format("Title: %s", suggestionManager.getTempSuggestion().getTitle()));
        super.getOptions().remove(String.format("Staff-in-charge: %s", super.getCampManager().getTempCamp().getStaffInCharge()));
        if (suggestionManager.getSelectedSuggestionStatus() == SuggestionStatus.PENDING) {
            super.getOptions().addAll(Arrays.asList(
                "(1) Edit suggestion",
                "(2) Delete suggestion"
            ));
        }
    }

    @Override
    public int selection() {
        IntInput selection = new GetSelectionUI(-1, 2);
        return selection.getValidInt("Your selection: ");
    }
}
