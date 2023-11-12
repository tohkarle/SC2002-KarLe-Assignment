package cams.view.camp;

import cams.Main;
import cams.component.AllCamps;
import cams.component.ConfirmOrDiscard;
import cams.component.IntInput;
import cams.component.LoadingIndicator;
import cams.util.Dismiss;

public class DeleteCampView {
    private AllCamps createdCamps;

    public DeleteCampView(AllCamps createdCamps) {
        this.createdCamps = createdCamps;
    }

    public void show() {
        // Show all created camps
        createdCamps.displayCamps("Select the camp you want to delete:");

        // Let user select the camp to delete
        int campID = createdCamps.selectCamp();
        if (campID == Dismiss.intOption()) { return; }

        // Confirm delete or discard and go back
        IntInput confirmOrDiscard = new ConfirmOrDiscard("delete");
        if (confirmOrDiscard.getValidInput() != 1) { return; }

        // Delete camp from campMap
        Main.campManager.deleteCamp(campID);
        LoadingIndicator.deleteLoadingIndicator("camp");
    }
}
