package cams.view.camp;

import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.option.camp.FacultyCampsOptions;
import cams.utils.Dismiss;
import cams.utils.FilterCamps;

public class FacultyCampsView implements View {

    private Navigation navigation;
    private Input getInput;
    private FilterCamps filterCamps;

    public FacultyCampsView(Navigation navigation, Input getInput, FilterCamps filterCamps) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.filterCamps = filterCamps;
    }

    public void render() {

        // Display camps
        Options facultyCampOptions = new FacultyCampsOptions(filterCamps);
        facultyCampOptions.display("Select camp to view details:");

        // Let user select camp to view details
        int selectedCampID = facultyCampOptions.selection();
        if (selectedCampID == Dismiss.intOption()) { 
            navigation.dismissView();
            return; 
        }

        if (selectedCampID == 0) {
            navigation.navigateTo(new FilterFacultyCampsView(navigation, getInput, filterCamps));
        } else {
            // Navigate to CampInfoView
            navigation.navigateTo(new CampInfoView(navigation, selectedCampID));
        }
    }
}