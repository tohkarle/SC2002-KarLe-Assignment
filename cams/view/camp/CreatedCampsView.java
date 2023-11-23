package cams.view.camp;

import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.option.camp.UserCampsOptions;
import cams.utils.Dismiss;
import cams.utils.FilterCamps;

public class CreatedCampsView implements View {

    private Navigation navigation;
    private Input getInput;
    private FilterCamps filterCamps;
    private String newCampName;
    private Options userCampOptions;

    public CreatedCampsView(Navigation navigation, Input getInput, FilterCamps filterCamps, String newCampName) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.filterCamps = filterCamps;
        this.newCampName = newCampName;
    }

    public CreatedCampsView(Navigation navigation, Input getInput, FilterCamps filterCamps) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.filterCamps = filterCamps;
        this.newCampName = null;
    }

    public void render() {

        userCampOptions = new UserCampsOptions(filterCamps);

        // Label camp as new if staff just created camp
        if (newCampName != null) { labelCampAsNew(); }

        // Display camps
        userCampOptions.display("Select camp to view details:");

        // Let user select camp to view details
        int selectedCampID = userCampOptions.selection();
        if (selectedCampID == Dismiss.intOption()) { 
            navigation.popToRoot();
            return; 
        }

        if (selectedCampID == 0) {
            navigation.navigateTo(new FilterCreatedCampsView(navigation, getInput, filterCamps));
        } else {
            // Navigate to EditCampView
            navigation.navigateTo(new CreatedCampInfoView(navigation, getInput, selectedCampID));
        }
    }

    public void labelCampAsNew() {
        if (newCampName != null) {
            userCampOptions.replaceOption(newCampName, newCampName + " (New)");
        }
    }
}
