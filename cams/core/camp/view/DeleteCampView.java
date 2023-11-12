package cams.core.camp.view;

import cams.Main;
import cams.component.LoadingIndicator;
import cams.component.SelectionInput;

public class DeleteCampView {
    private AllCampsView createdCamps;

    public DeleteCampView(AllCampsView createdCamps) {
        this.createdCamps = createdCamps;
    }

    public void show() {
        // Show all created camps
        createdCamps.displayCamps("Select the camp you want to delete:");

        // Let user select the camp to delete
        if (createdCamps.getIds().size() == 0) {
            if (SelectionInput.selectionInputFieldWithBack(-1, -1) == SelectionInput.backOptionInt()) { return; };
        } else {
            int option = SelectionInput.selectionInputFieldWithBack(1, createdCamps.getIds().size());

            // Go back if user selects back
            if (option == SelectionInput.backOptionInt()) { return; }

            // Confirm delete or discard and go back
            if (SelectionInput.confirmOrDiscard("delete") != 1) { return; };

            // Delete camp from campMap
            int campID = createdCamps.getIds().get(option - 1);
            Main.campManager.deleteCamp(campID);

            LoadingIndicator.deleteLoadingIndicator("camp");
        }
    }
}
