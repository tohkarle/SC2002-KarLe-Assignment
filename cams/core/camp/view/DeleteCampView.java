package cams.core.camp.view;

import java.util.ArrayList;

import cams.Main;
import cams.core.root.view.RootView;
import cams.util.UIComponents;

public class DeleteCampView {
    private int staffID;

    public DeleteCampView(RootView rootView) {
        this.staffID = rootView.getCurrentUserID();
    }

    public void show() {
        
        // Fetch all camps created by staff
        ArrayList<Integer> createdCampIDs = Main.campManager.getAllStaffCampIDs(staffID);
        ArrayList<String> createdCampNames = Main.campManager.getAllStaffCampNames(staffID);

        if (createdCampIDs.size() == 0) {
            UIComponents.pageHeader("No camp has been created.");

            // Get user input
            if (UIComponents.navigationInput(-1, -1) == UIComponents.backOptionInt()) { return; };

        } else {
            UIComponents.pageHeader("Select the camp you want to delete.");

            for (int i = 0; i < createdCampNames.size(); i++) {
                System.out.println("(" + (i + 1) + ") " + createdCampNames.get(i));
            }

            // Get user input
            int option = UIComponents.navigationInput(1, createdCampIDs.size());

            // Go back if user selects back
            if (option == UIComponents.backOptionInt()) { return; }

            // Confirm delete or discard and go back
            UIComponents.confirmOrDiscard("delete");
            if (Main.scanner.nextInt() != 1) { return; }

            // Delete camp from campMap
            int campID = createdCampIDs.get(option - 1);
            Main.campManager.deleteCamp(campID);

            UIComponents.deleteLoadingIndicator("camp");
        }
    }
}
