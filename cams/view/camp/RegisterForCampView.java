package cams.view.camp;

import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.option.camp.FacultyCampsOptions;
import cams.ui.camp.RegisterForCampUI;
import cams.utils.Dismiss;

public class RegisterForCampView implements View {

    private Navigation navigation;
    private Input getInput;

    public RegisterForCampView(Navigation navigation, Input getInput) {
        this.navigation = navigation;
        this.getInput = getInput;
    }

    public void render() {
        // Show all available camps
        Options facultyCampOptions = new FacultyCampsOptions();
        facultyCampOptions.display("Select the camp you wish to register. Do note that committee members are only allowed to register other camps as attendee.");

        // Let user select the camp to register
        int selectedCampID = facultyCampOptions.selection();
        if (selectedCampID == Dismiss.intOption()) { 
            navigation.dismissView();
            return; 
        }

        // Register for camp
        UI registerForCampUI = new RegisterForCampUI(getInput, selectedCampID);
        registerForCampUI.body();
    }
}
