package cams.ui.camp;

import cams.components.LoadingIndicator;
import cams.interfaces.IntInput;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.CampManager;

public class DeleteCampUI implements UI {

    private Navigation navigation;
    private CampManager campManager;
    private IntInput confirm;

    public DeleteCampUI(Navigation navigation, CampManager campManager, IntInput confirm) {
        this.navigation = navigation;
        this.campManager = campManager;
        this.confirm = confirm;
    }

    @Override
    public void body() {
        // Confirm delete or discard and go back
        if (confirm.getValidInt("Canfirm delete?") != 1) { return; }

        // Delete camp
        campManager.deleteCamp(campManager.getSelectedID());
        LoadingIndicator.deleteLoadingIndicator("camp");
        navigation.dismissView();
    }
}
