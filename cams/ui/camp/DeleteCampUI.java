package cams.ui.camp;

import cams.Main;
import cams.components.LoadingIndicator;
import cams.interfaces.InputField;
import cams.interfaces.IntInput;
import cams.ui.ConfirmOrDiscardUI;
import cams.utils.CampUtil;

public class DeleteCampUI implements InputField {

    private CampUtil campUtil;

    public DeleteCampUI(CampUtil campUtil) {
        this.campUtil =  campUtil;
    }

    public boolean focused() {
        // Confirm delete or discard and go back
        IntInput confirmOrDiscard = new ConfirmOrDiscardUI("delete");
        if (confirmOrDiscard.getValidInt() != 1) { return true; }

        // Delete camp from campMap
        Main.campManager.deleteCamp(campUtil.getId());
        Main.campManager.save();
        LoadingIndicator.deleteLoadingIndicator("camp");
        return false;
    }
}
