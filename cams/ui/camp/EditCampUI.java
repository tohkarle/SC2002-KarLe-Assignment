package cams.ui.camp;

import cams.components.LoadingIndicator;
import cams.components.option.Options;
import cams.interfaces.IntInput;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.CampManager;
import cams.model.Camp;
import cams.option.camp.EditCampInfoOptions;
import cams.ui.ConfirmOrDiscardUI;

public class EditCampUI implements UI {

    private Navigation navigation;
    private Camp camp;
    private int selectedEditField;

    public EditCampUI(Navigation navigation, Camp camp, int selectedEditField) {
        this.camp = camp;
        this.navigation = navigation;
        this.selectedEditField = selectedEditField;
    }

    @Override
    public void body() {
        
        CampManager campManager = CampManager.getInstance();
        IntInput confirm = new ConfirmOrDiscardUI();
        Options editCampInfoOptions = new EditCampInfoOptions();

        // Create and initialize all UIs for edit camp
        UI editCampNameUI = new EditCampNameUI(camp, editCampInfoOptions.getOption(0));
        UI editCampFacultyUI = new EditCampFacultyUI(camp, editCampInfoOptions.getOption(1));
        UI editCampLocationUI = new EditCampLocationUI(camp, editCampInfoOptions.getOption(2));
        UI editCampDescriptionUI = new EditCampDescriptionUI(camp, editCampInfoOptions.getOption(3));
        UI editCampVisibility = new EditCampVisibilityUI(camp, editCampInfoOptions.getOption(4));
        UI editCampDatesUI = new EditCampDatesUI(camp, editCampInfoOptions.getOption(5), editCampInfoOptions.getOption(6));
        UI editCampRegiatrationClosingDateUI = new EditCampRegiatrationClosingDateUI(camp, editCampInfoOptions.getOption(7));
        UI editCampTotalSlotsUI = new EditCampTotalSlotsUI(camp, campManager, editCampInfoOptions.getOption(8));
        UI editCampCommitteeSlotsUI = new EditCampCommitteeSlotsUI(camp, campManager, editCampInfoOptions.getOption(9));

        UI[] editCampUIs = new UI[] {
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

        // Display corresponding UI
        if (selectedEditField <= editCampUIs.length) {
            editCampUIs[selectedEditField - 1].body();
        } else if (selectedEditField == editCampUIs.length + 1) {
            // Confirm changes or discard
            if (confirm.getValidInt("Confirm changes?") != 1) { return; }
            LoadingIndicator.editingLoadingIndicator("camp");
            campManager.updateCamp(camp);
            navigation.dismissView();
        }
    }
}
