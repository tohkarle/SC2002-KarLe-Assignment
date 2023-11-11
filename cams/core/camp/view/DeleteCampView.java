package cams.core.camp.view;

import java.util.ArrayList;

import cams.Main;
import cams.core.root.view.RootView;
import cams.manager.StaffManager;
import cams.util.UIComponents;

public class DeleteCampView {
    private int staffID;
    private StaffManager manager;

    public DeleteCampView(RootView rootView) {
        this.staffID = rootView.getCurrentUserID();
        this.manager = (StaffManager) rootView.getManager();
    }

    public void show() {
        ArrayList<Integer> createdCampIDs = this.manager.getCreatedCampIDs(staffID);

        if (createdCampIDs.size() == 0) {
            UIComponents.pageHeader("No camp has been created.");
        } else {
            UIComponents.pageHeader("Select the camp you want to delete.");

            for (int i = 0; i < createdCampIDs.size(); i++) {
                System.out.println("(" + (i + 1) + ") " + Main.campManager.getCampName(createdCampIDs.get(i)));
            }

            int option = UIComponents.navigationInput(1, createdCampIDs.size());

            // Go back if user selects back
            if (option == UIComponents.backOptionInt()) { return; }

            // Confirm delete or discard and go back
            UIComponents.confirmOrDiscard("delete");
            if (Main.scanner.nextInt() != 1) { return; }

            // Delete camp from campMap
            int campID = createdCampIDs.get(option - 1);
            Main.campManager.deleteCamp(campID);

            // Remove camp from user
            this.manager.removeFromUserCampIDs(staffID, campID);

            UIComponents.deleteLoadingIndicator("camp");
        }
    }
}
