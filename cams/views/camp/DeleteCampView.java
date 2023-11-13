package cams.views.camp;

import cams.Main;
import cams.components.LoadingIndicator;
import cams.interfaces.IntInput;
import cams.interfaces.View;
import cams.ui.ConfirmOrDiscardUI;
import cams.utils.Dismiss;

public class DeleteCampView implements View {
    
    private GetCamps createdCamps;

    public DeleteCampView(GetCamps createdCamps) {
        this.createdCamps = createdCamps;
    }

    public void body() {
        // Show all created camps
        createdCamps.displayCamps("Select the camp you want to delete:");

        // Let user select the camp to delete
        int campID = createdCamps.selectCamp();
        if (campID == Dismiss.intOption()) { return; }

        // Confirm delete or discard and go back
        IntInput confirmOrDiscard = new ConfirmOrDiscardUI("delete");
        if (confirmOrDiscard.getValidInt() != 1) { return; }

        // Delete camp from campMap
        Main.campManager.deleteCamp(campID);
        LoadingIndicator.deleteLoadingIndicator("camp");
    }
}
