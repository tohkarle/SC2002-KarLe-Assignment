package cams.ui.camp;

import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.model.Camp;
import cams.utils.FilterCamps;

public class FilterFacultyCampsUI implements UI {
    
    private Navigation navigation;
    private Input getInput;
    private FilterCamps filterCamps;
    private int selectedFilterBy;

    public FilterFacultyCampsUI(Navigation navigation, Input getInput, FilterCamps filterCamps, int selectedFilterBy) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.filterCamps = filterCamps;
        this.selectedFilterBy = selectedFilterBy;
    }

    @Override
    public void body() {

        if (selectedFilterBy == 0) {
            navigation.dismissView();
            return;
        }

        Camp camp = filterCamps.getCamp();

        // Create and initialize all UIs for edit camp
        UI[] filterCampInfoUis = new UI[] {
            new CampNameUI(getInput, camp, "Filter camps by name that contains: "),
            new CampLocationUI(getInput, camp, "Filter camps by location that contains: "), 
            new CampDescriptionUI(getInput, camp, "Filter camps by description that contains: "),
            new CampDatesUI(getInput, camp, "Filter camps by dates within the given dates. Enter start date: ", "Enter end date: "),
            new CampRegiatrationClosingDateUI(getInput, camp, "Filter camps by registration closing: "),
            new CampTotalSlotsUI(getInput, camp, "Filter camps by total slots: "),
            new CampCommitteeSlotsUI(getInput, camp, "Filter camps by committee slots: "),
            new CampStaffInChargeUI(getInput, camp, "Filter camps by staff-in-charge: "),
        };

        // Display corresponding UI
        if (selectedFilterBy <= filterCampInfoUis.length) {
            filterCampInfoUis[selectedFilterBy - 1].body();
            navigation.dismissView();
        } 
    }
}
