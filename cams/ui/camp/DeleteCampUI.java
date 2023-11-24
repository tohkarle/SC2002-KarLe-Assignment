package cams.ui.camp;

import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.CampManager;
import cams.utils.LoadingIndicator;

/**
 * UI object for deleting a camp
 */
public class DeleteCampUI implements UI {

    private Navigation navigation;
    private int selectedCampID;
    private Input getInput;

    /**
     * Initialize the DeleteCampUI
     * @param navigation Navigation object used to navigate between views
     * @param selectedCampID ID of the camp to be deleted
     * @param getInput Input object used to get user input
     */
    public DeleteCampUI(Navigation navigation, int selectedCampID, Input getInput) {
        this.navigation = navigation;
        this.selectedCampID = selectedCampID;
        this.getInput = getInput;
    }

    /**
     * Executes user interaction logic for deleting a camp
     */
    @Override
    public void body() {
        
        CampManager campManager = CampManager.getInstance();

        // Confirm delete or discard and go back
        if (getInput.confirmOrDiscard("Confirm delete?") != 1) { return; }

        // Delete camp
        campManager.deleteCamp(selectedCampID);
        LoadingIndicator.deleteLoadingIndicator("camp");
        navigation.dismissView();
    }
}
