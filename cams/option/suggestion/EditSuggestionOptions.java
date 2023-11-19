package cams.option.suggestion;

import java.util.Arrays;

import cams.interfaces.IntInput;
import cams.manager.CampManager;
import cams.manager.SuggestionManager;
import cams.option.camp.CampInfoOptions;
import cams.ui.GetSelectionWithDismissUI;
import cams.utils.Page;

public class EditSuggestionOptions extends CampInfoOptions {

    private SuggestionManager suggestionManager;
    
    public EditSuggestionOptions(CampManager campManager, SuggestionManager suggestionManager) {
        super(campManager);
        this.suggestionManager = suggestionManager;
    }

    @Override
    public void updateCampInfo() {
        super.updateCampInfo();
        super.getOptions().add(0, String.format("Title: %s", suggestionManager.getTempSuggestion().getTitle()));
        super.getOptions().remove(String.format("Staff-in-charge: %s", super.getCampManager().getTempCamp().getStaffInCharge()));
        super.getOptions().addAll(Arrays.asList(
            "Update changes"
        ));
    }

    @Override
    public void display(String title) {
        Page.header(title);
        super.printOptions();
    }

    @Override
    public int selection() {
        IntInput selectionWithDismiss = new GetSelectionWithDismissUI(1, super.getOptionsSize());
        return selectionWithDismiss.getValidInt("Your selection: ");
    }
}
