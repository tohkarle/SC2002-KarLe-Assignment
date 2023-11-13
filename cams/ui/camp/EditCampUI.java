package cams.ui.camp;

import cams.Main;
import cams.components.LoadingIndicator;
import cams.components.option.EditCampInfoOptions;
import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.IntInput;
import cams.ui.ConfirmOrDiscardUI;
import cams.utils.CampUtil;

public class EditCampUI implements Input {

    private int option;
    private Input[] editCampUIs;
    private Options editCampInfoOptions;
    private CampUtil campUtil;

    // Edit camp UIs
    private Input editCampNameUI;
    private Input editCampFacultyUI;
    private Input editCampLocationUI;
    private Input editCampDescriptionUI;
    private Input editCampVisibility;
    private Input editCampRegiatrationClosingDateUI;
    private Input editCampDatesUI;
    private Input editCampTotalSlotsUI;
    private Input editCampCommitteeSlotsUI;

    public EditCampUI(int option, CampUtil campUtil) {

        this.campUtil = campUtil;
        this.option = option;
        this.editCampInfoOptions = new EditCampInfoOptions();

        // Initialize all UIs
        this.editCampNameUI = new CampNameUI(campUtil, editCampInfoOptions.getOption(0));
        this.editCampFacultyUI = new CampFacultyUI(campUtil, editCampInfoOptions.getOption(1));
        this.editCampLocationUI = new CampLocationUI(campUtil, editCampInfoOptions.getOption(2));
        this.editCampDescriptionUI = new CampDescriptionUI(campUtil, editCampInfoOptions.getOption(3));
        this.editCampVisibility = new CampVisibilityUI(campUtil, editCampInfoOptions.getOption(4));
        this.editCampRegiatrationClosingDateUI = new CampRegiatrationClosingDateUI(campUtil, editCampInfoOptions.getOption(5));
        this.editCampDatesUI = new CampDatesUI(campUtil, editCampInfoOptions.getOption(6), editCampInfoOptions.getOption(7));
        this.editCampTotalSlotsUI = new CampTotalSlotsUI(campUtil, editCampInfoOptions.getOption(8));
        this.editCampCommitteeSlotsUI = new CampCommitteeSlotsUI(campUtil, editCampInfoOptions.getOption(9));

        this.editCampUIs = new Input[] {
            this.editCampNameUI,
            this.editCampFacultyUI,
            this.editCampLocationUI,
            this.editCampDescriptionUI,
            this.editCampVisibility,
            this.editCampRegiatrationClosingDateUI,
            this.editCampDatesUI,
            this.editCampTotalSlotsUI,
            this.editCampCommitteeSlotsUI
        };
    }

    public boolean getInput() {
        
        // Display corresponding UI
        if (option <= editCampUIs.length) {
            this.editCampUIs[option - 1].getInput();
        } else if (option == editCampUIs.length + 1) {
            campUtil.updateCamp();
            return false;
        } else if (option == editCampUIs.length + 5) {

            // Confirm delete or discard and go back
            IntInput confirmOrDiscard = new ConfirmOrDiscardUI("delete");
            if (confirmOrDiscard.getValidInt() != 1) { return true; }

            // Delete camp from campMap
            Main.campManager.deleteCamp(campUtil.getId());
            Main.campManager.save();
            LoadingIndicator.deleteLoadingIndicator("camp");
            return false;
        }
        return true;
    }
}
