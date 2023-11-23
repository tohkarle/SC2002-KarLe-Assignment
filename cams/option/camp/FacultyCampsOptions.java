package cams.option.camp;

import cams.manager.CampManager;
import cams.manager.UserManager;

public class FacultyCampsOptions extends AllCampsOptions {

    @Override
    public void fetchCamps() {
        
        UserManager userManager = UserManager.getInstance();
        CampManager campManager = CampManager.getInstance();

        // Fetch all camps from faculty
        super.setNoCampTitle("No camp has been created under this faculty. Please conme back at a later time.");
        super.setOptions(campManager.getAllFacultyCampNames(userManager.getCurrentUser().getFaculty()));
        super.setCampIDs(campManager.getAllFacultyCampIDs(userManager.getCurrentUser().getFaculty()));
    }
}
