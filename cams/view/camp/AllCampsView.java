package cams.view.camp;

import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.option.camp.AllCampsOptions;
import cams.utils.Dismiss;
import cams.utils.FilterCamps;

/**
 * View object for All Camps page
 */
public class AllCampsView implements View {

    private Navigation navigation;
    private Input getInput;
    private FilterCamps filterCamps;

    /**
     * Initialize the AllCampsView
     * @param navigation Navigation object used to control navigation of the application
     * @param getInput Input object used to get input from user
     * @param filterCamps FilterCamps object containing the applied filters
     */
    public AllCampsView(Navigation navigation, Input getInput, FilterCamps filterCamps) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.filterCamps = filterCamps;
    }

    /**
     * Render the AllCampsView
     */
    public void render() {

        // Display camps
        Options allCampOptions = new AllCampsOptions(filterCamps);
        allCampOptions.display("Select camp to view details:");

        // Let user select camp to view details
        int selectedCampID = allCampOptions.selection();
        if (selectedCampID == Dismiss.intOption()) { 
            navigation.dismissView();
            return; 
        }

        if (selectedCampID == 0) {
            navigation.navigateTo(new FilterAllCampsView(navigation, getInput, filterCamps));
        } else {
            // Navigate to CampInfoView
            navigation.navigateTo(new CampInfoView(navigation, selectedCampID));
        }
    }
}
