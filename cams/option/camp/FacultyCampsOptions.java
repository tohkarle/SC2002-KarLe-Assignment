package cams.option.camp;

import java.util.ArrayList;

import cams.manager.CampManager;
import cams.manager.UserManager;
import cams.model.Camp;
import cams.utils.FilterCamps;

/**
 * Options object for camp selection in the FacultyCampsView
 */
public class FacultyCampsOptions extends AllCampsOptions {

    /**
     * Initialize the camp selection options
     * @param filterCamps FilterCamps object containing the applied filters
     */
    public FacultyCampsOptions(FilterCamps filterCamps) {
        super(filterCamps);
    }

    @Override
    public void fetchCamps(FilterCamps filterCamps) {
        UserManager userManager = UserManager.getInstance();
        CampManager campManager = CampManager.getInstance();

        // Fetch all camps from faculty
        super.setNoCampTitle("No camp has been created under this faculty. Please come back at a later time.");
        
        ArrayList<Camp> camps = filterCamps.filteredCamps(campManager.getFacultyCampsByNameSorted(userManager.getCurrentUser().getFaculty()));
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
