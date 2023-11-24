package cams.view.camp;

import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.option.camp.FilterUserCampsOptions;
import cams.ui.camp.FilterCreatedCampsUI;
import cams.utils.Dismiss;
import cams.utils.FilterCamps;

/**
 * View object for Filter Created Camps page
 */
public class FilterCreatedCampsView implements View {

    private Navigation navigation;
    private Input getInput;
    private FilterCamps filterCamps;

    /**
     * Initialize the FilterCreatedCampsView
     * @param navigation Navigation object used to control navigation of the application
     * @param getInput Input object used to get input from user
     * @param filterCamps FilterCamps object containing the applied filters
     */
    public FilterCreatedCampsView(Navigation navigation, Input getInput, FilterCamps filterCamps) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.filterCamps = filterCamps;
    }

    /**
     * Render the FilterCreatedCampsView
     */
    public void render() {

        Options filterUserCampsOptions = new FilterUserCampsOptions();
        filterUserCampsOptions.display("Please select the field you want to filter by.");

        int option = filterUserCampsOptions.selection();
        if (option == Dismiss.intOption()) { 
            navigation.dismissView();
            return; 
        }

        switch (option) {
            case 0:
                filterCamps.setSelectedFilterBy("");
                break;
            case 1:
                filterCamps.setSelectedFilterBy("campName");
                break;
            case 2:
                filterCamps.setSelectedFilterBy("userGroup");
                break;
            case 3:
                filterCamps.setSelectedFilterBy("location");
                break;
            case 4:
                filterCamps.setSelectedFilterBy("description");
                break;
            case 5:
                filterCamps.setSelectedFilterBy("dates");
                break;
            case 6:
                filterCamps.setSelectedFilterBy("registrationClosingDate");
                break;
            case 7:
                filterCamps.setSelectedFilterBy("totalslots");
                break;
            case 8:
                filterCamps.setSelectedFilterBy("committeeSlots");
                break;
        }

        UI filterCreatedCampsUI = new FilterCreatedCampsUI(navigation, getInput, filterCamps, option);
        filterCreatedCampsUI.body();
    }
}
