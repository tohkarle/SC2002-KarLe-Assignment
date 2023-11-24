package cams.view.camp;

import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.option.camp.FacultyCampsOptions;
import cams.ui.camp.RegisterForCampUI;
import cams.utils.Dismiss;
import cams.utils.FilterCamps;

/**
 * View object for Register For Camp page
 */
public class RegisterForCampView implements View {

    private Navigation navigation;
    private Input getInput;
    private FilterCamps filterCamps;

    /**
     * Initialize the RegisterForCampView
     * @param navigation Navigation object used to control navigation of the application
     * @param getInput Input object used to get input from user
     * @param filterCamps FilterCamps object used to filter camps
     */
    public RegisterForCampView(Navigation navigation, Input getInput, FilterCamps filterCamps) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.filterCamps = filterCamps;
    }

    /**
     * Render the RegisterForCampView
     */
    public void render() {
        // Show all available camps
        Options facultyCampOptions = new FacultyCampsOptions(filterCamps);
        facultyCampOptions.display("Select the camp you wish to register. Do note that committee members are only allowed to register other camps as attendee.");

        // Let user select the camp to register
        int selectedCampID = facultyCampOptions.selection();
        if (selectedCampID == Dismiss.intOption()) { 
            navigation.dismissView();
            return; 
        }

        if (selectedCampID == 0) {
            navigation.navigateTo(new FilterFacultyCampsView(navigation, getInput, filterCamps));
        } else {
            // Register for camp
            UI registerForCampUI = new RegisterForCampUI(getInput, selectedCampID);
            registerForCampUI.body();
        }
    }
}
