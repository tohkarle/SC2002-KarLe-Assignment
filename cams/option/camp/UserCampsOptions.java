package cams.option.camp;

import java.util.ArrayList;

import cams.manager.CampManager;
import cams.manager.UserManager;
import cams.model.Camp;
import cams.utils.FilterCamps;

/**
 * Options object for camp selection in the CreatedCampsView and RegisteredCampsView
 */
public class UserCampsOptions extends AllCampsOptions {

    /**
     * Initialize the camp selection options
     * @param filterCamps FilterCamps object containing the applied filters
     */
    public UserCampsOptions(FilterCamps filterCamps) {
        super(filterCamps);
    }


    /**
     * A method to build the options list
     */
    @Override
    public void fetchCamps(FilterCamps filterCamps) {
        CampManager campManager = CampManager.getInstance();
        UserManager userManager = UserManager.getInstance();
        
        ArrayList<Camp> camps;
        ArrayList<String> campNames = new ArrayList<>();
        ArrayList<Integer> campIds = new ArrayList<>();
        
        if (userManager.isStaff() ) {
            // Fetch all camps created by staff
            super.setNoCampTitle("No camp has been created by you. Go to 'Create camp' to create a new camp.");
            camps = filterCamps.filteredCamps(campManager.getStaffCampsByNameSorted(userManager.getCurrentUser().getName()));
        } else {
            // Fetch all camps registered by student
            super.setNoCampTitle("You have not registered for any camp. Go to 'Register for camps' to register for camps under your faculty.");
            camps = filterCamps.filteredCamps(campManager.getRegisteredCampsByNameSorted(userManager.getCurrentUser().getName()));
        }
        
        for (Camp camp : camps) {
            campNames.add(camp.getCampName());
            campIds.add(camp.getId());
        }
        
        super.setOptions(campNames);
        super.setCampIDs(campIds);
    }
}
