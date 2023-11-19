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
    
    private Options EditCampInfoOptions;
    private Navigation navigation;
    private CampManager campManager;
    private SuggestionManager suggestionManager;
    private IntInput confirm;

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

    public EditSuggestionUI(Navigation navigation, CampManager campManager, SuggestionManager suggestionManager, Options EditCampInfoOptions, IntInput confirm) {
        this.navigation = navigation;
        this.EditCampInfoOptions = EditCampInfoOptions;
        this.campManager = campManager;
        this.suggestionManager = suggestionManager;
        this.confirm = confirm;
    }

    @Override
    public void body() {

        Suggestion tempSuggestion = suggestionManager.getTempSuggestion();
        Camp tempCamp = tempSuggestion.getCamp();

        // Create and initialize all UIs for edit suggestion
        editSuggestionTitleUI = new EditSuggestionTitleUI(tempSuggestion, "Edit title: ");
        editCampNameUI = new EditCampNameUI(tempCamp, EditCampInfoOptions.getOption(0));
        editCampFacultyUI = new EditCampFacultyUI(tempCamp, EditCampInfoOptions.getOption(1));
        editCampLocationUI = new EditCampLocationUI(tempCamp, EditCampInfoOptions.getOption(2));
        editCampDescriptionUI = new EditCampDescriptionUI(tempCamp, EditCampInfoOptions.getOption(3));
        editCampVisibility = new EditCampVisibilityUI(tempCamp, EditCampInfoOptions.getOption(4));
        editCampDatesUI = new EditCampDatesUI(tempCamp, EditCampInfoOptions.getOption(5), EditCampInfoOptions.getOption(6));
        editCampRegiatrationClosingDateUI = new EditCampRegiatrationClosingDateUI(tempCamp, EditCampInfoOptions.getOption(7));
        editCampTotalSlotsUI = new EditCampTotalSlotsUI(tempCamp, campManager, EditCampInfoOptions.getOption(8));
        editCampCommitteeSlotsUI = new EditCampCommitteeSlotsUI(tempCamp, campManager, EditCampInfoOptions.getOption(9));

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
        if (campManager.getSelectedCampInfo() <= editCampUIs.length) {
            editCampUIs[campManager.getSelectedCampInfo() - 1].body();
        } else if (campManager.getSelectedCampInfo() == editCampUIs.length + 1) {            
            // Confirm changes and submit or discard
            if (confirm.getValidInt("Confirm changes?") != 1) { return; }
            suggestionManager.updateSuggestion(suggestionManager.getTempSuggestion());
            LoadingIndicator.submitLoadingIndicator("suggestion");
            navigation.dismissView();
        }
    }
}
