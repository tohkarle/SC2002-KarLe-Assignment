package cams.view.camp;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.manager.CampManager;
import cams.utils.Dismiss;

public class RegisterForCampView extends View {

    private CampManager campManager;

    // Options:
    private Options facultyCampOptions;

    // UI:
    private UI registerForCampUI;

    public RegisterForCampView(Navigation navigation, CampManager campManager) {
        super(navigation);
        this.campManager = campManager;
    }

    public void render() {
        while (true) {
            // Show all available camps
            facultyCampOptions = super.getOptions("camp.FacultyCampOptions");
            facultyCampOptions.display("Select the camp you wish to register. Do note that committee members are only allowed to register other camps as attendee");

            // Let user select the camp to register
            int campID = facultyCampOptions.selection();
            if (campID == Dismiss.intOption()) { 
                super.getNavigation().dismissView();
                return; 
            }
            campManager.setSelectedCampID(campID);

            // Register for camp
            registerForCampUI = super.getUI("camp.RegisterForCampUI");
            registerForCampUI.body();
        }
    }
}
