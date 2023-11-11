package cams.core.camp.view;

import cams.Main;
import cams.component.LoadingIndicator;
import cams.component.UserInput;

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
            if (UserInput.selectionInputField(-1, -1) == UserInput.backOptionInt()) { return; };
        } else {
            int option = UserInput.selectionInputField(1, createdCamps.getIds().size());

            // Go back if user selects back
            if (option == UserInput.backOptionInt()) { return; }

            // Confirm delete or discard and go back
            UserInput.confirmOrDiscard("delete");
            if (Main.scanner.nextInt() != 1) { return; }

            // Delete camp from campMap
            int campID = createdCamps.getIds().get(option - 1);
            Main.campManager.deleteCamp(campID);

            LoadingIndicator.deleteLoadingIndicator("camp");
        }
    }
}
