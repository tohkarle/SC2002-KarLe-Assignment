package cams.option.camp;

import cams.manager.CampManager;
import cams.manager.UserManager;

public class UserCampsOptions extends AllCampsOptions {

    @Override
    public void fetchCamps() {
        CampManager campManager = CampManager.getInstance();
        UserManager userManager = UserManager.getInstance();
        
        if (userManager.isStaff() ) {
            // Fetch all camps created by staff
            super.setNoCampTitle("No camp has been created by you. Go to 'Create camp' to create a new camp.");
            super.setOptions(campManager.getAllStaffCampNames(userManager.getCurrentUser().getName()));
            super.setCampIDs(campManager.getAllStaffCampIDs(userManager.getCurrentUser().getName()));
        } else {
            // Fetch all camps registered by student
            super.setNoCampTitle("You have not registered for any camp. Go to 'Register for camps' to register for camps under your faculty.");
            super.setOptions(campManager.getAllRegisteredCampNames(userManager.getCurrentUser().getName()));
            super.setCampIDs(campManager.getAllRegisteredCampIDs(userManager.getCurrentUser().getName()));
        }
    }
}
