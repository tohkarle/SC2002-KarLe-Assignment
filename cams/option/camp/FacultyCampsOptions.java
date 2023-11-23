package cams.option.camp;

import java.util.ArrayList;

import cams.manager.CampManager;
import cams.manager.UserManager;
import cams.model.Camp;

public class FacultyCampsOptions extends AllCampsOptions {

    @Override
    public void fetchCamps() {
        UserManager userManager = UserManager.getInstance();
        CampManager campManager = CampManager.getInstance();

        // Fetch all camps from faculty
        super.setNoCampTitle("No camp has been created under this faculty. Please come back at a later time.");
        
        ArrayList<Camp> camps = campManager.getFacultyCampsByNameSorted(userManager.getCurrentUser().getFaculty());
        ArrayList<String> campNames = new ArrayList<>();
        ArrayList<Integer> campIds = new ArrayList<>();
        
        for (Camp camp : camps) {
            campNames.add(camp.getCampName());
            campIds.add(camp.getId());
        }
        
        super.setOptions(campNames);
        super.setCampIDs(campIds);
    }
}
