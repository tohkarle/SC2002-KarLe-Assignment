package cams.ui.suggestion;

import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.SuggestionManager;
import cams.model.Camp;
import cams.model.Suggestion;
import cams.option.camp.EditCampInfoOptions;
import cams.ui.camp.CampCommitteeSlotsUI;
import cams.ui.camp.CampDatesUI;
import cams.ui.camp.CampDescriptionUI;
import cams.ui.camp.CampFacultyUI;
import cams.ui.camp.CampLocationUI;
import cams.ui.camp.CampNameUI;
import cams.ui.camp.CampRegiatrationClosingDateUI;
import cams.ui.camp.CampTotalSlotsUI;
import cams.ui.camp.CampVisibilityUI;
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

        SuggestionManager suggestionManager = SuggestionManager.getInstance();
        Options editCampInfoOptions = new EditCampInfoOptions();
        Camp camp = suggestion.getCamp();

        // Create and initialize all UIs for edit suggestion
        UI[] editCampUIs = new UI[] {
            new EditSuggestionTitleUI(getInput, suggestion, "Edit title: "),
            new CampNameUI(getInput, camp, editCampInfoOptions.getOption(0)),
            new CampFacultyUI(getInput, camp, editCampInfoOptions.getOption(1)),
            new CampLocationUI(getInput, camp, editCampInfoOptions.getOption(2)),
            new CampDescriptionUI(getInput, camp, editCampInfoOptions.getOption(3)),
            new CampVisibilityUI(camp, editCampInfoOptions.getOption(4)),
            new CampDatesUI(getInput, camp, editCampInfoOptions.getOption(5), editCampInfoOptions.getOption(6)),
            new CampRegiatrationClosingDateUI(getInput, camp, editCampInfoOptions.getOption(7)),
            new CampTotalSlotsUI(getInput, camp, editCampInfoOptions.getOption(8)),
            new CampCommitteeSlotsUI(getInput, camp, editCampInfoOptions.getOption(9)),
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
