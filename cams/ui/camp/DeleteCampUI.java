package cams.ui.camp;

import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.CampManager;
import cams.utils.LoadingIndicator;

public class DeleteCampUI implements UI {

    private Navigation navigation;
    private int selectedCampID;
    private Input getInput;

    public DeleteCampUI(Navigation navigation, int selectedCampID, Input getInput) {
        this.navigation = navigation;
        this.selectedCampID = selectedCampID;
        this.getInput = getInput;
    }

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
