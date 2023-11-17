package cams.option.camp;

import java.util.ArrayList;

import cams.components.option.SelectableOptions;
import cams.manager.CampManager;
import cams.manager.UserManager;
import cams.utils.Dismiss;
import cams.utils.Page;

public class UserCampOptions extends SelectableOptions {

    private String noCampTitle;
    private ArrayList<Integer> campIDs;

    public UserCampOptions(UserManager userManager, CampManager campManager) {
        if (userManager.isStaff() ) {
            // Fetch all camps created by staff
            this.noCampTitle = "No camp has been created by you. Go to 'Create camp' to create a new camp.";
            super.setOptions(campManager.getAllStaffCampNames(userManager.getCurrentUser().getName()));
            this.campIDs = campManager.getAllStaffCampIDs(userManager.getCurrentUser().getName());
        } else {
            // Fetch all camps registered by student
            this.noCampTitle = "You have not registered for any camp. Go to 'Register for camps' to register for camps under your faculty.";
            super.setOptions(campManager.getAllRegisteredCampNames(userManager.getCurrentUser().getName()));
            this.campIDs = campManager.getAllRegisteredCampIDs(userManager.getCurrentUser().getName());
        }
    }

    @Override
    public void display(String title) {
        if (super.getOptionsSize() == 0) {
            Page.header(this.noCampTitle);
        } else {
            super.display(title);
        }
    }

    @Override
    public int selection() {
        int option = super.selection();
        if (option == Dismiss.intOption()) { return option; }
        return this.campIDs.get(option - 1);
    }
}
