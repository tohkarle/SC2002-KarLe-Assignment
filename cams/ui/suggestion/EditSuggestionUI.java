package cams.ui.suggestion;

import cams.components.LoadingIndicator;
import cams.components.option.Options;
import cams.interfaces.IntInput;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.CampManager;
import cams.manager.SuggestionManager;
import cams.model.Camp;
import cams.model.Suggestion;
import cams.option.camp.EditCampInfoOptions;
import cams.ui.ConfirmOrDiscardUI;
import cams.ui.camp.EditCampCommitteeSlotsUI;
import cams.ui.camp.EditCampDatesUI;
import cams.ui.camp.EditCampDescriptionUI;
import cams.ui.camp.EditCampFacultyUI;
import cams.ui.camp.EditCampLocationUI;
import cams.ui.camp.EditCampNameUI;
import cams.ui.camp.EditCampRegiatrationClosingDateUI;
import cams.ui.camp.EditCampTotalSlotsUI;
import cams.ui.camp.EditCampVisibilityUI;

public class EditSuggestionUI implements UI {
    
    private Navigation navigation;
    private Suggestion suggestion;
    private int selectedCampInfo;

    // Edit suggestion involves editing camp details
    private UI editSuggestionTitleUI;
    private UI editCampNameUI;
    private UI editCampFacultyUI;
    private UI editCampLocationUI;
    private UI editCampDescriptionUI;
    private UI editCampVisibility;
    private UI editCampRegiatrationClosingDateUI;
    private UI editCampDatesUI;
    private UI editCampTotalSlotsUI;
    private UI editCampCommitteeSlotsUI;

    public EditSuggestionUI(Navigation navigation, Suggestion suggestion, int selectedCampInfo) {
        this.navigation = navigation;
        this.suggestion = suggestion;
        this.selectedCampInfo = selectedCampInfo;
    }

    @Override
    public void body() {

        CampManager campManager = CampManager.getInstance();
        SuggestionManager suggestionManager = SuggestionManager.getInstance();
        IntInput confirm = new ConfirmOrDiscardUI();
        Options editCampInfoOptions = new EditCampInfoOptions();
        Camp camp = suggestion.getCamp();

        // Create and initialize all UIs for edit suggestion
        editSuggestionTitleUI = new EditSuggestionTitleUI(suggestion, "Edit title: ");
        editCampNameUI = new EditCampNameUI(camp, editCampInfoOptions.getOption(0));
        editCampFacultyUI = new EditCampFacultyUI(camp, editCampInfoOptions.getOption(1));
        editCampLocationUI = new EditCampLocationUI(camp, editCampInfoOptions.getOption(2));
        editCampDescriptionUI = new EditCampDescriptionUI(camp, editCampInfoOptions.getOption(3));
        editCampVisibility = new EditCampVisibilityUI(camp, editCampInfoOptions.getOption(4));
        editCampDatesUI = new EditCampDatesUI(camp, editCampInfoOptions.getOption(5), editCampInfoOptions.getOption(6));
        editCampRegiatrationClosingDateUI = new EditCampRegiatrationClosingDateUI(camp, editCampInfoOptions.getOption(7));
        editCampTotalSlotsUI = new EditCampTotalSlotsUI(camp, campManager, editCampInfoOptions.getOption(8));
        editCampCommitteeSlotsUI = new EditCampCommitteeSlotsUI(camp, campManager, editCampInfoOptions.getOption(9));

        UI[] editCampUIs = new UI[] {
            editSuggestionTitleUI,
            editCampNameUI,
            editCampFacultyUI,
            editCampLocationUI,
            editCampDescriptionUI,
            editCampVisibility,
            editCampDatesUI,
            editCampRegiatrationClosingDateUI,
            editCampTotalSlotsUI,
            editCampCommitteeSlotsUI
        };

        // Edit camp details
        if (selectedCampInfo <= editCampUIs.length) {
            editCampUIs[selectedCampInfo - 1].body();
        } else if (selectedCampInfo == editCampUIs.length + 1) {            
            // Confirm changes and submit or discard
            if (confirm.getValidInt("Confirm changes?") != 1) { return; }
            suggestionManager.updateSuggestion(suggestion);
            LoadingIndicator.submitLoadingIndicator("suggestion");
            navigation.dismissView();
        }
    }
}
