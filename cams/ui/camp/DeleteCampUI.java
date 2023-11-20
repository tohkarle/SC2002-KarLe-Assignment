package cams.ui.camp;

import cams.components.LoadingIndicator;
import cams.interfaces.IntInput;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.CampManager;
import cams.ui.ConfirmOrDiscardUI;

public class DeleteCampUI implements UI {

    private Navigation navigation;
    private int selectedCampID;

    public DeleteCampUI(Navigation navigation, int selectedCampID) {
        this.navigation = navigation;
        this.selectedCampID = selectedCampID;
    }

    @Override
    public void body() {
        
        CampManager campManager = CampManager.getInstance();
        IntInput confirm = new ConfirmOrDiscardUI();

        // Confirm delete or discard and go back
        if (confirm.getValidInt("Confirm delete?") != 1) { return; }

        // Delete camp
        campManager.deleteCamp(selectedCampID);
        LoadingIndicator.deleteLoadingIndicator("camp");
        navigation.dismissView();
    }
}
