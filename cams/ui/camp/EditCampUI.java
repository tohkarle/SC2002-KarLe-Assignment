package cams.ui.camp;

import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.CampManager;
import cams.model.Camp;
import cams.option.camp.EditCampInfoOptions;
import cams.utils.LoadingIndicator;

public class EditCampUI implements UI {

    private Navigation navigation;
    private Input getInput;
    private Camp camp;
    private int selectedEditField;

    public EditCampUI(Navigation navigation, Input getInput, Camp camp, int selectedEditField) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.camp = camp;
        this.selectedEditField = selectedEditField;
    }

    @Override
    public void body() {
        
        CampManager campManager = CampManager.getInstance();
        Options editCampInfoOptions = new EditCampInfoOptions();

        // Create and initialize all UIs for edit camp
        UI[] editCampUIs = new UI[] {
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

        // Display corresponding UI
        if (selectedEditField <= editCampUIs.length) {
            editCampUIs[selectedEditField - 1].body();
        } else if (selectedEditField == editCampUIs.length + 1) {
            // Confirm changes or discard
            if (getInput.confirmOrDiscard("Confirm changes?") != 1) { return; }
            if (!campManager.updateCampSuccessful(camp)) { return; }
            
            LoadingIndicator.editingLoadingIndicator("camp");
            navigation.dismissView();
        }
    }
}
