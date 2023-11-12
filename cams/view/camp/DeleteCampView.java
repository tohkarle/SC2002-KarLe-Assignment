package cams.view.camp;

import cams.Main;
import cams.component.ConfirmOrDiscard;
import cams.component.IntInput;
import cams.component.LoadingIndicator;

public class DeleteCampView {
    private AllCampsView createdCamps;

    public DeleteCampView(AllCampsView createdCamps) {
        this.createdCamps = createdCamps;
    }

    public void show() {
        // Show all created camps
        createdCamps.displayCamps("Select the camp you want to delete:");

        // Let user select the camp to delete
        createdCamps.selectCamp();
        int campID = createdCamps.getSelectedCampID();

        // Confirm delete or discard and go back
        IntInput confirmOrDiscard = new ConfirmOrDiscard("delete");
        if (confirmOrDiscard.getValidInput() != 1) { return; }

        // Delete camp from campMap
        Main.campManager.deleteCamp(campID);
        LoadingIndicator.deleteLoadingIndicator("camp");
    }
}
