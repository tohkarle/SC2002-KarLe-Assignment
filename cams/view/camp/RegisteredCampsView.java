package cams.view.camp;

import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.option.camp.UserCampsOptions;
import cams.utils.Dismiss;
import cams.utils.FilterCamps;

public class RegisteredCampsView implements View {

    private Navigation navigation;
    private Input getInput;
    private FilterCamps filterCamps;

    public RegisteredCampsView(Navigation navigation, Input getInput, FilterCamps filterCamps) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.filterCamps = filterCamps;
    }

    public void render() {

        // Display camps
        Options userCampOptions = new UserCampsOptions(filterCamps);

        // Display registered camps
        userCampOptions.display("Select camp to view details:");

        // Let user select camp to view details
        int selectedCampID = userCampOptions.selection();
        if (selectedCampID == Dismiss.intOption()) { 
            navigation.dismissView();
            return; 
        }

        if (selectedCampID == 0) {
            navigation.navigateTo(new FilterFacultyCampsView(navigation, getInput, filterCamps));
        } else {
            // Navigate to RegisteredCampInfoView
            navigation.navigateTo(new RegisteredCampInfoView(navigation, getInput, selectedCampID));
        }
    }
}
