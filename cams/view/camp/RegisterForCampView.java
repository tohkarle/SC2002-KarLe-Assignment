package cams.view.camp;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.option.camp.FacultyCampOptions;
import cams.ui.camp.RegisterForCampUI;
import cams.utils.Dismiss;

public class RegisterForCampView implements View {

    private Navigation navigation;

    public RegisterForCampView(Navigation navigation) {
        this.navigation = navigation;
    }

    public void render() {
        while (true) {
            // Show all available camps
            Options facultyCampOptions = new FacultyCampOptions();
            facultyCampOptions.display("Select the camp you wish to register. Do note that committee members are only allowed to register other camps as attendee.");

            // Let user select the camp to register
            int selectedCampID = facultyCampOptions.selection();
            if (selectedCampID == Dismiss.intOption()) { 
                navigation.dismissView();
                return; 
            }

            // Register for camp
            UI registerForCampUI = new RegisterForCampUI(selectedCampID);
            registerForCampUI.body();
        }
    }
}
