package cams.ui.suggestion;

import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.CampManager;
import cams.manager.SuggestionManager;
import cams.model.Camp;
import cams.model.Suggestion;
import cams.option.camp.EditCampInfoOptions;
import cams.ui.camp.EditCampCommitteeSlotsUI;
import cams.ui.camp.EditCampDatesUI;
import cams.ui.camp.EditCampDescriptionUI;
import cams.ui.camp.EditCampFacultyUI;
import cams.ui.camp.EditCampLocationUI;
import cams.ui.camp.EditCampNameUI;
import cams.ui.camp.EditCampRegiatrationClosingDateUI;
import cams.ui.camp.EditCampTotalSlotsUI;
import cams.ui.camp.EditCampVisibilityUI;
import cams.utils.LoadingIndicator;

public class EditSuggestionUI implements UI {
    
    private Navigation navigation;
    private Input getInput;
    private Suggestion suggestion;
    private int selectedCampInfo;

    public EditSuggestionUI(Navigation navigation, Input getInput, Suggestion suggestion, int selectedCampInfo) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.suggestion = suggestion;
        this.selectedCampInfo = selectedCampInfo;
    }

    @Override
    public void body() {

        CampManager campManager = CampManager.getInstance();
        SuggestionManager suggestionManager = SuggestionManager.getInstance();
        Options editCampInfoOptions = new EditCampInfoOptions();
        Camp camp = suggestion.getCamp();

        // Create and initialize all UIs for edit suggestion
        UI[] editCampUIs = new UI[] {
            new EditSuggestionTitleUI(getInput, suggestion, "Edit title: "),
            new EditCampNameUI(getInput, camp, editCampInfoOptions.getOption(0)),
            new EditCampFacultyUI(getInput, camp, editCampInfoOptions.getOption(1)),
            new EditCampLocationUI(getInput, camp, editCampInfoOptions.getOption(2)),
            new EditCampDescriptionUI(getInput, camp, editCampInfoOptions.getOption(3)),
            new EditCampVisibilityUI(camp, editCampInfoOptions.getOption(4)),
            new EditCampDatesUI(getInput, camp, editCampInfoOptions.getOption(5), editCampInfoOptions.getOption(6)),
            new EditCampRegiatrationClosingDateUI(getInput, camp, editCampInfoOptions.getOption(7)),
            new EditCampTotalSlotsUI(getInput, camp, campManager, editCampInfoOptions.getOption(8)),
            new EditCampCommitteeSlotsUI(getInput, camp, campManager, editCampInfoOptions.getOption(9)),
        };

        // Edit camp details
        if (selectedCampInfo <= editCampUIs.length) {
            editCampUIs[selectedCampInfo - 1].body();
        } else if (selectedCampInfo == editCampUIs.length + 1) {            
            // Confirm changes and submit or discard
            if (getInput.confirmOrDiscard("Confirm changes?") != 1) { return; }
            suggestionManager.updateSuggestion(suggestion);
            LoadingIndicator.submitLoadingIndicator("suggestion");
            navigation.dismissView();
        }
    }
}
